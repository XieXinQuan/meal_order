package com.quan.entity.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/16
 */
@Data
public class AddShopEmployeeRequest {

    @NotNull(message = "用户不能为空")
    private Integer userId;

    @NotNull(message = "店铺不能为空")
    private Integer shopId;

}
