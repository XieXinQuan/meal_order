package com.quan.Enum;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/13
 */
public enum SexEnum implements ValueReplace{
    /**
     * 返回枚举
     */
    MALE("M", "男"),
    FEMALE("F", "女"),
    UNKNOWN("N", "未知");

    private final String key;
    private final String value;

    SexEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 获取枚举值
     * @return
     */
    public String getKey() {
        return key;
    }

    /**
     * 获取枚举解释
     * @return
     */
    public String getValue() {
        return value;
    }
}
