package com.quan.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Administrator
 */
public class TOrder {
    private Long id;

    private String serialNumber;

    private Long userId;

    private Long commodityId;

    private Long productId;

    private BigDecimal money;

    private BigDecimal amount;

    private BigDecimal price;

    private Integer count;

    private BigDecimal totalPrice;

    private Long shippingAddressId;

    private Byte status;

    private Long createUser;

    private Long updateUser;

    private Date createTime;

    private Date updateTime;

}