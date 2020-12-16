package com.quan.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.quan.Enum.CommonByteEnum;
import com.quan.Enum.ResultEnum;
import com.quan.Enum.UserTypeEnum;
import com.quan.constant.Constant;
import com.quan.entity.Email;
import com.quan.entity.WxLogin;
import com.quan.entity.request.WxLoginRequest;
import com.quan.entity.response.SearchByNameResponse;
import com.quan.entity.response.UserInfoResponse;
import com.quan.exception.GlobalException;
import com.quan.feign.WxFeign;
import com.quan.model.User;
import com.quan.model.WxUser;
import com.quan.repository.UserRepository;
import com.quan.repository.WxUserRepository;
import com.quan.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/11/14
 */
@Service
@Slf4j
public class UserService extends BaseService{

    @Resource
    private UserRepository userRepository;
    @Resource
    private Email email;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private WxFeign wxFeign;
    @Resource
    private WxLogin wxLogin;
    @Resource
    private WxUserRepository wxUserRepository;


    public Integer registerUser(String name, String email, String password, String code) {
        //先校验Redis Code
        Object redisCode = redisTemplate.opsForValue().get(Constant.redisLoginCodePre + email);
        if (!code.equals(redisCode)) {
            throw new GlobalException(ResultEnum.CustomException.getKey(), "验证码错误.");
        }

        //检验用户名
        User isExists = userRepository.findFirstByUserName(name);
        if (isExists != null) {
            throw new GlobalException(ResultEnum.CustomException.getKey(), "用户名已存在.");
        }

        //设置用户信息
        User user = new User();
        user.setEmail(email);
        user.setUserName(name);
        user.setPassword(password);
        //设置默认值
        user.setType(UserTypeEnum.NormalUser.getKey());
        user.setStatus(CommonByteEnum.Normal.getKey());
        userRepository.save(user);

        //删除redis的验证码
        redisTemplate.delete(Constant.redisLoginCodePre + email);
        return user.getId();
    }

    public Integer login(String name, String password) {
        User user = userRepository.findFirstByUserName(name);

        String dbPassword = Optional.ofNullable(user).map(User::getPassword).orElse(null);

        if (!com.alibaba.druid.util.StringUtils.equals(password, dbPassword)) {
            throw new GlobalException(ResultEnum.CustomException.getKey(), "用户名或密码错误.");
        }

        assert user != null;
        return user.getId();
    }

    public int code(String loginEmail) {
        if (StringUtils.isEmpty(loginEmail)) {
            throw new GlobalException(ResultEnum.CustomException.getKey(), "手机或邮箱为空");
        }
        int code = RandomUtil.randomInt(1000, 9999);

        String redisKey = Constant.redisLoginCodePre + loginEmail;
        redisTemplate.opsForValue().set(redisKey, String.valueOf(code), 5L, TimeUnit.MINUTES);
        email.sendEmail(loginEmail, "验证码", "您的验证码为: " + code);
        return code;
    }

    public String wxLogin(WxLoginRequest wxLoginRequest){
        log.info("发起微信小程序的获取用户openid接口 -- Request : {code: {}}", wxLoginRequest);
        JSONObject wxUserInfo = wxFeign.getWxUserInfo(wxLogin.getAppId(), wxLogin.getAppSecret(), wxLoginRequest.getCode(), wxLogin.getGrantType());
        log.info("发起微信小程序的获取用户openid接口 -- Response : {}", wxUserInfo);

        String openid = wxUserInfo.getString("openid");
        if (org.apache.commons.lang.StringUtils.isEmpty(openid)){
            return "微信登录失败, 请授权";
        }

        //查询数据库是否登录过
        WxUser firstByWxAppId = wxUserRepository.findFirstByWxAppId(openid);
        WxUser wxUser = new WxUser();
        if (firstByWxAppId == null){
            //创建user 设置用户信息
            User user = new User();
            user.setUserName(wxLoginRequest.getWxNickName());
            int randomPassword = RandomUtil.randomInt(100000, 999999);
            user.setPassword(String.valueOf(randomPassword));
            //设置默认值
            user.setType(UserTypeEnum.NormalUser.getKey());
            user.setStatus(CommonByteEnum.Normal.getKey());
            userRepository.saveAndFlush(user);

            wxUser.setUserId(user.getId());
        }

        BeanUtils.copyProperties(wxLoginRequest, wxUser);
        wxUser.setWxAppId(openid);
        wxUserRepository.save(wxUser);
        return "微信授权成功";
    }

    public UserInfoResponse userInfo(){
        User currentUser = this.getCurrentUser();

        UserInfoResponse userInfo = UserInfoResponse.builder()
            .loginName(currentUser.getUserName())
            .nickName(currentUser.getNickName())
            .firstWorkTime(currentUser.getCreateTime())
            .inWorkTime(DateUtil.betweenDay(new Date(), currentUser.getCreateTime(), true))
            .workNumber(currentUser.getId())
            .shopName(currentUser.getNickName())
            .employeeName(currentUser.getNickName())
            .sex(currentUser.getSex())
            .build();
        return userInfo;
    }

    public List<SearchByNameResponse> searchUserByName(String name){
        List<User> users = userRepository.findAllByNickNameLikeAndStatus("%"+name+"%", CommonByteEnum.Normal.getKey());
        if (CollectionUtils.isEmpty(users)){
            return Lists.newArrayListWithCapacity(0);
        }
        List<SearchByNameResponse> result = Lists.newArrayListWithCapacity(users.size());
        users.stream().forEach(user -> {
            SearchByNameResponse searchUserResponse = new SearchByNameResponse();
            searchUserResponse.setId(user.getId());
            searchUserResponse.setName(user.getNickName());
            result.add(searchUserResponse);
        });
        return result;
    }
}
