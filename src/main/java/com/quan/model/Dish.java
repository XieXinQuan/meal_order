package com.quan.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/13
 */
@Data
@Entity
@Table(name = "dish")
@EntityListeners(AuditingEntityListener.class)
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer shopId;

    private String dishName;

    private String dishShortName;

    private Byte category;

    private BigDecimal discount;

    private BigDecimal price;

    private String unit;

    private String imgUrl;

    private Integer stock;

    private Byte status;

    @CreatedBy
    private Integer createUser;

    @LastModifiedBy
    private Integer updateUser;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;
}
