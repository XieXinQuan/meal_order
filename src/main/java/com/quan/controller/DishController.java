package com.quan.controller;

import com.quan.entity.request.AddDishRequest;
import com.quan.entity.request.ListByCategoryRequest;
import com.quan.entity.request.ListDishRequest;
import com.quan.entity.response.DishListResponse;
import com.quan.entity.response.PageResponse;
import com.quan.service.DishService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/17
 */
@RestController
@RequestMapping("/dish")
public class DishController {

    @Resource
    private DishService dishService;

    @PostMapping("/add")
    public Object add(@Validated @RequestBody AddDishRequest addDishRequest){
        dishService.addDish(addDishRequest.getShopId(), addDishRequest.getDishName(),
                addDishRequest.getCategory(), addDishRequest.getPrice(), addDishRequest.getUnit(),
                addDishRequest.getStock(), addDishRequest.getImageUrl());
        return "添加成功";
    }

    @PostMapping("/list")
    public Object list(@RequestBody ListDishRequest request){
        PageResponse<DishListResponse> dishListResponsePageResponse = dishService.dishList(request);
        return dishListResponsePageResponse;
    }

    @GetMapping("/listByCategory")
    public Object listByCategory(@Validated @RequestBody ListByCategoryRequest listByCategoryRequest){
        return dishService.listByCategoryRequests(listByCategoryRequest.getShopId(), listByCategoryRequest.getCategory());
    }
}
