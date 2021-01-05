package com.quan.entity.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.quan.Enum.DishCategoryEnum;
import com.quan.annotation.ReturnReplace;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/21
 */
@Data
public class DishListResponse {

    private Integer id;

    private String dishName;

    private String shopName;

    @ReturnReplace(DishCategoryEnum.class)
    private Byte category;

    @JSONField(format = ",##0.00")
    private BigDecimal price;

    private String uploadUserName;

    private String imgUrl;
}
