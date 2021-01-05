package com.quan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/13
 */
@Data
@Entity
@Table(name = "dish")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer shopId;

    private String dishName;

    private String dishShortName;

    private Byte category;

    private BigDecimal disCount;

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

    @OneToOne
    @JoinColumn(name="shopId",referencedColumnName = "id", insertable = false, updatable = false)
    private Shop shop;
    @OneToOne
    @JoinColumn(name="createUser",referencedColumnName = "id", insertable = false, updatable = false)
    private User user;
}
