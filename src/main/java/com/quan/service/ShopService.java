package com.quan.service;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.quan.Enum.CommonByteEnum;
import com.quan.Enum.ResultEnum;
import com.quan.Enum.UserTypeEnum;
import com.quan.entity.response.SearchByNameResponse;
import com.quan.entity.response.ShopEmployeeList;
import com.quan.entity.response.ShopListResponse;
import com.quan.exception.GlobalException;
import com.quan.model.Shop;
import com.quan.model.ShopEmployee;
import com.quan.model.User;
import com.quan.repository.ShopEmployeeRepository;
import com.quan.repository.ShopRepository;
import com.quan.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/13
 */
@Service
public class ShopService extends BaseService{
    @Resource
    private ShopRepository shopRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private ShopEmployeeRepository shopEmployeeRepository;

    public void addShop(String shopName, Integer shopManager, String location){
        //校验店铺名
        Shop isExists = shopRepository.findFirstByNameAndStatus(shopName, CommonByteEnum.Normal.getKey());
        if (isExists != null){
            throw new GlobalException(ResultEnum.CustomException.getKey(), "店铺名称 : " + shopName + " 已存在.");
        }
        //确立shopManager的身份 -- 不能是用户角色
        User isUser = userRepository.findFirstByIdAndAndType(shopManager, UserTypeEnum.NormalUser.getKey());
        if (isUser == null){
            throw new GlobalException(ResultEnum.CustomException.getKey(), "店铺经理不能是用户角色");
        }
        Shop shop = new Shop();
        shop.setName(shopName);
        shop.setShopAdmin(1);
        shop.setShopManager(shopManager);
        shop.setStatus(CommonByteEnum.Normal.getKey());
        shop.setLocation(location);
        shopRepository.save(shop);
    }

    public List<ShopListResponse> list(){
        List<Shop> shopList = shopRepository.findAllByShopManagerEqualsOrCreateUserOrderByIdAsc(this.getCurrentUserId(), this.getCurrentUserId());
        List<ShopListResponse> result = Lists.newArrayListWithCapacity(shopList.size());
        for (Shop shop : shopList){
            ShopListResponse shopListResponse = new ShopListResponse();
            shopListResponse.setDate(shop.getCreateTime());
            shopListResponse.setId(shop.getId());
            shopListResponse.setShopName(shop.getName());
            shopListResponse.setShopManager(shop.getShopManagerUser().getNickName());
            shopListResponse.setCreateUserName(shop.getCreatedUser().getNickName());
            shopListResponse.setStatus(shop.getStatus());
            result.add(shopListResponse);
        }
        return result;
    }

    public void addShopEmployee(Integer userId, Integer shopId){
        //校验userId 和 shopId
        User user = userRepository.findById(userId).orElse(null);
        if (user == null){
            throw new GlobalException(ResultEnum.CustomException.getKey(), "找不到该用户.");
        }
        Shop shop = shopRepository.findById(shopId).orElse(null);
        if (shop == null){
            throw new GlobalException(ResultEnum.CustomException.getKey(), "找不到该店铺");
        }
        List<ShopEmployee> isExists = shopEmployeeRepository.findAllByShopId(shopId);
        if (CollectionUtil.isNotEmpty(isExists)){
            List<Integer> lists = isExists.stream().map(ShopEmployee::getUserId).collect(Collectors.toList());
            if (lists.contains(userId)){
                throw new GlobalException(ResultEnum.CustomException.getKey(), "该员工已经是店铺员工");
            }
        }
        ShopEmployee shopEmployee = new ShopEmployee();
        shopEmployee.setShopId(shopId);
        shopEmployee.setUserId(userId);
        shopEmployee.setStatus(CommonByteEnum.Normal.getKey());
        shopEmployee.setType(UserTypeEnum.ShopUser.getKey());
        shopEmployeeRepository.save(shopEmployee);
    }

    public List<SearchByNameResponse> searchByNameList(String name){
        List<Shop> shopList = shopRepository.findAllByNameLikeAndStatus("%" + name + "%", CommonByteEnum.Normal.getKey());
        if (CollectionUtils.isEmpty(shopList)){
            return Collections.EMPTY_LIST;
        }
        List<SearchByNameResponse> result = Lists.newArrayListWithCapacity(shopList.size());
        shopList.stream().forEach(shop -> {
            SearchByNameResponse searchByNameResponse = new SearchByNameResponse();
            searchByNameResponse.setId(shop.getId());
            searchByNameResponse.setName(shop.getName());
            result.add(searchByNameResponse);
        });
        return result;
    }

    public List shopEmployeeList(){
        List<ShopEmployee> list = shopEmployeeRepository.findAllByUserIdOrCreateUser(getCurrentUserId(), getCurrentUserId());
        if (CollectionUtil.isEmpty(list)){
            return Collections.EMPTY_LIST;
        }
        List<ShopEmployeeList> result = Lists.newArrayListWithCapacity(list.size());
        list.stream().forEach(shopEmployee -> {
            ShopEmployeeList shopEmployeeList = new ShopEmployeeList();
            shopEmployeeList.setId(shopEmployee.getId());
            shopEmployeeList.setDate(shopEmployee.getCreateTime());
            shopEmployeeList.setPosition(shopEmployee.getType());
            shopEmployeeList.setUserName(shopEmployee.getUser().getNickName());
            shopEmployeeList.setShopName(shopEmployee.getShop().getName());
            result.add(shopEmployeeList);
        });
        return result;
    }
}
