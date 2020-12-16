package com.quan.service;

import com.google.common.collect.Lists;
import com.quan.Enum.CommonByteEnum;
import com.quan.Enum.ResultEnum;
import com.quan.Enum.UserTypeEnum;
import com.quan.entity.response.ShopListResponse;
import com.quan.exception.GlobalException;
import com.quan.model.Shop;
import com.quan.model.User;
import com.quan.repository.ShopRepository;
import com.quan.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}
