package com.quan.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/11/29
 */
@Data
@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userName;

    private String nickName;

    private String password;

    private String email;

    private String weixinId;

    private String  weixinName;

    private String sex;

    private Byte type;

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
