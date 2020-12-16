package com.quan.Enum;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/5/3
 */
public enum DishCategoryEnum {
    /**
     * 商品类型
     */
    VEGETABLE(1, "素菜"),
    MEAT(2, "肉类"),
    DRINK(3, "饮料"),
    FRUIT(4, "水果");

    private final Byte key;
    private final String value;


    DishCategoryEnum(int key, String value) {
        this.key = (byte)(key & 0xFF);
        this.value = value;
    }

    public Byte getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
