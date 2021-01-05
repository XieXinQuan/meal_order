package com.quan.entity.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2021/1/2
 */
@Data
public class WxShopDishResponse {

    private Integer id;

    private String dishName;

    @JSONField(format = "0.00")
    private BigDecimal disCount;

    private String imgUrl;

    @JSONField(format = "0.00")
    private BigDecimal price;
}
