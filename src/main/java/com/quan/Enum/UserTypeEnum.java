package com.quan.Enum;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/11/29
 */
public enum UserTypeEnum implements BaseByteEnum{
    /**
     * 公共字节枚举
     */
    Admin(0, "管理员"),
    ShopAdmin(1, "店铺管理员"),
    ShopUser(2, "店员"),
    NormalUser(2, "用户");

    private final Byte key;
    private final String value;

    UserTypeEnum(int key, String value) {
        this.key = (byte)(key & 0xFF);
        this.value = value;
    }

    @Override
    public Byte getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }
}
