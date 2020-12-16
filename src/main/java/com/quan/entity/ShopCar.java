package com.quan.entity;

import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/5/23
 */
public class ShopCar {
    private Long id;

    private Long userId;

    private Long commodityId;

    private Integer num;

    private Byte status;

    private Long createUser;

    private Long updateUser;

    private Date createTime;

    private Date updateTime;
}
