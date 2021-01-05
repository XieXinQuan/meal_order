package com.quan.entity.request;

import com.quan.Enum.DishCategoryEnum;
import com.quan.annotation.EnumValue;
import lombok.Data;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2021/1/2
 */
@Data
public class ListByCategoryRequest {
    @EnumValue(value = DishCategoryEnum.class, message = "菜品分类选择错误")
    private Byte category;

    private Integer shopId;

}
