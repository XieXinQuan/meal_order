package com.quan.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 */
@Data
public class Shop {
    private Long id;

    private String shopName;

    private String createUser;

    private String updateUser;

    private Date createTime;

    private Date updateTime;

}