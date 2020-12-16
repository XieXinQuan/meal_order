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
 * DATE:2020/12/12
 */
@Data
@Entity
@Table(name = "shop")
@EntityListeners(AuditingEntityListener.class)
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String shortName;

    private Integer shopAdmin;

    private Integer shopManager;

    private String location;

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
    @JoinColumn(name="shopManager",referencedColumnName = "id", insertable = false, updatable = false)
    private User shopManagerUser;
    @OneToOne
    @JoinColumn(name="createUser",referencedColumnName = "id", insertable = false, updatable = false)
    private User createdUser;
    @OneToOne
    @JoinColumn(name="updateUser",referencedColumnName = "id", insertable = false, updatable = false)
    private User updatedUser;
}
