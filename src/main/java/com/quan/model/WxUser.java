package com.quan.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/5
 */
@Data
@Entity
@Table(name = "wx_user")
@EntityListeners(AuditingEntityListener.class)
public class WxUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private String wxAppId;

    private String wxNickName;

    private String wxAvatarUrl;

    private Integer gender;

    private String province;

    private String city;

    private String country;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;
}
