package com.quan.entity.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/13
 */
@Data
public class AddShopRequest {

    @Size(min = 5, max = 50, message = "店铺名称字数在5-50字之间")
    private String shopName;

    @NotNull(message = "店铺经理不能为空")
    private Integer shopManager;

    @Size(min = 5, max = 200, message = "地址字数在5-200之间")
    private String location;
}
