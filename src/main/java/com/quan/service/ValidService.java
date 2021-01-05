package com.quan.service;

import com.quan.Enum.ResultEnum;
import com.quan.exception.GlobalException;
import com.quan.model.Shop;
import com.quan.repository.ShopRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2021/1/2
 */
@Service
public class ValidService {
    @Resource
    private ShopRepository shopRepository;

    public void validShopId(Integer shopId){
        if (shopId == null || shopId <= 0){
            throw new GlobalException(ResultEnum.CustomException.getKey(), "店铺不存在");
        }
        Shop shop = shopRepository.findById(shopId).orElse(null);
        if (shop == null || shop.getId() == null){
            throw new GlobalException(ResultEnum.CustomException.getKey(), "店铺不存在");
        }
    }
}
