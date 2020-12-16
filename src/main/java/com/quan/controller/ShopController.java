package com.quan.controller;

import com.quan.entity.request.AddShopRequest;
import com.quan.service.ShopService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/13
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Resource
    private ShopService shopService;

    @PostMapping("/add")
    public Object add(@Validated @RequestBody AddShopRequest shopRequest){
        shopService.addShop(shopRequest.getShopName(), shopRequest.getShopManager(), shopRequest.getLocation());
        return "添加店铺成功";
    }

    @GetMapping("/list")
    public Object add(){

        return shopService.list();
    }
}
