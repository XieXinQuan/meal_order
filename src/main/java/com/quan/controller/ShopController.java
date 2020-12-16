package com.quan.controller;

import com.quan.entity.request.AddShopEmployeeRequest;
import com.quan.entity.request.AddShopRequest;
import com.quan.entity.request.SearchByNameRequest;
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

    @PostMapping("/addEmployee")
    public Object addEmployee(@Validated @RequestBody AddShopEmployeeRequest addShopEmployeeRequest){
        shopService.addShopEmployee(addShopEmployeeRequest.getUserId(), addShopEmployeeRequest.getShopId());
        return "添加成功";
    }

    @GetMapping("/searchShopByName")
    public Object searchUserByName(@Validated SearchByNameRequest searchByNameRequest){
        return shopService.searchByNameList(searchByNameRequest.getName());
    }

    @GetMapping("/employeeList")
    public Object employeeList(){
        return shopService.shopEmployeeList();
    }
}
