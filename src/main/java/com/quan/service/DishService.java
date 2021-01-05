package com.quan.service;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.quan.Enum.CommonByteEnum;
import com.quan.Enum.ResultEnum;
import com.quan.entity.request.ListDishRequest;
import com.quan.entity.response.DishListResponse;
import com.quan.entity.response.PageResponse;
import com.quan.entity.response.WxShopDishResponse;
import com.quan.exception.GlobalException;
import com.quan.model.Dish;
import com.quan.model.Shop;
import com.quan.model.ShopEmployee;
import com.quan.model.User;
import com.quan.repository.DishRepository;
import com.quan.repository.ShopRepository;
import com.quan.repository.UserRepository;
import com.quan.util.PageUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/17
 */
@Service
public class DishService extends BaseService{

    @Resource
    private DishRepository dishRepository;
    @Resource
    private ShopRepository shopRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private ValidService validService;

    public void addDish(Integer shopId, String dishName, Byte category,
                        BigDecimal price, String unit, Integer stock, String imgUrl){
        //检验shopId
        Shop shop = shopRepository.findById(shopId).orElse(null);
        if (shop == null){
            throw new GlobalException(ResultEnum.CustomException.getKey(), "店铺不存在");
        }
        //校验菜名
        Dish isExistsDish = dishRepository.findFirstByShopIdAndDishNameAndStatus(shopId, dishName, CommonByteEnum.Normal.getKey());
        if (isExistsDish != null){
            throw new GlobalException(ResultEnum.CustomException.getKey(), "该店铺已有该菜名");
        }
        //入库
        Dish dish = Dish.builder().dishName(dishName)
                .category(category)
                .price(price)
                .shopId(shopId)
                .disCount(new BigDecimal("10"))
                .status(CommonByteEnum.Normal.getKey())
                .stock(stock)
                .unit(unit)
                .imgUrl(imgUrl)
                .build();
        dishRepository.save(dish);

    }

    public PageResponse<DishListResponse> dishList(ListDishRequest request){
        //查询出我的所有店铺
        User user = userRepository.findById(this.getCurrentUserId()).orElse(null);
        List<ShopEmployee> createEmployees = user.getCreateEmployees();
        List<ShopEmployee> employees = user.getEmployees();

        Set<Integer> shopIds = Sets.newLinkedHashSet();
        if (CollectionUtil.isNotEmpty(createEmployees)){
            List<Integer> collect = createEmployees.stream().map(ShopEmployee::getShopId).collect(Collectors.toList());
            shopIds.addAll(collect);
        }
        if (CollectionUtil.isNotEmpty(employees)){
            List<Integer> collect = employees.stream().map(ShopEmployee::getShopId).collect(Collectors.toList());
            shopIds.addAll(collect);
        }
        Pageable pageable = PageUtil.getSimplePageableByRequest(request);
        Page<Dish> dishs = dishRepository.findAllByShopIdInOrderByCreateTimeDesc(shopIds, pageable);
        if (CollectionUtil.isEmpty(dishs) || CollectionUtils.isEmpty(dishs.getContent())){
            return PageUtil.getEmptyPageResponse();
        }
        List<DishListResponse> dishListResponses = Lists.newArrayList();
        dishs.getContent().stream().forEach(dish -> {
            DishListResponse dishListResponse = new DishListResponse();
            dishListResponse.setId(dish.getId());
            dishListResponse.setCategory(dish.getCategory());
            dishListResponse.setPrice(dish.getPrice());
            dishListResponse.setDishName(dish.getDishName());
            dishListResponse.setShopName(dish.getShop().getName());
            dishListResponse.setUploadUserName(dish.getUser().getNickName());
            dishListResponse.setImgUrl(dish.getImgUrl());
            dishListResponses.add(dishListResponse);
        });
        return PageUtil.getPageResponse(dishListResponses, dishs.getTotalElements());
    }

    public List<WxShopDishResponse> listByCategoryRequests(Integer shopId, Byte category){
        validService.validShopId(shopId);
        List<Dish> allByShopIdAndCategory = dishRepository.findAllByShopIdAndCategory(shopId, category);

        List<WxShopDishResponse> result = Lists.newArrayList();
        if (CollectionUtil.isEmpty(allByShopIdAndCategory)){
            return result;
        }
        allByShopIdAndCategory.stream().forEach(dish -> {
            WxShopDishResponse wxShopDishResponse = new WxShopDishResponse();
            BeanUtils.copyProperties(dish, wxShopDishResponse);
            result.add(wxShopDishResponse);
        });
        return result;
    }
}
