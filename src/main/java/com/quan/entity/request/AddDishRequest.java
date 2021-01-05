package com.quan.entity.request;

import com.quan.Enum.DishCategoryEnum;
import com.quan.annotation.EnumValue;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/17
 */
@Data
public class AddDishRequest {

    @NotNull(message = "店铺不能为空")
    private Integer shopId;

    @Size(min = 1, max = 20, message = "菜名字数在1-20字之间")
    private String dishName;

    @EnumValue(value = DishCategoryEnum.class, message = "分类选择错误")
    private Byte category;

    @DecimalMin(value = "0.1", message = "价格不低于0.1元")
    @DecimalMax(value = "10000", message = "价格不超过1万")
    @NotNull(message = "价格不能为空")
    private BigDecimal price;

    @Size(min = 1, max = 2, message = "单位字数在1-2字之间")
    private String unit;

    @NotNull(message = "库存不能为空")
    @Min(value = 1, message = "库存最低为1")
    @Max(value = 999999, message = "库存最大为999999")
    private Integer stock;

    @NotBlank(message = "图片不能为空")
    private String imageUrl;

}
