package com.quan.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/13
 */
@Data
@Entity
@Table(name = "shop_employee")
@EntityListeners(AuditingEntityListener.class)
public class ShopEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer shopId;

    private Byte status;

    private Byte type;

    @CreatedBy
    private Integer createUser;

    @LastModifiedBy
    private Integer updateUser;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;

    @OneToOne
    @JoinColumn(name="userId",referencedColumnName = "id", insertable = false, updatable = false)
    private User User;
    @OneToOne
    @JoinColumn(name="shopId",referencedColumnName = "id", insertable = false, updatable = false)
    private Shop shop;
    @OneToOne
    @JoinColumn(name="updateUser",referencedColumnName = "id", insertable = false, updatable = false)
    private User updatedUser;
}
