package com.quan.entity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/5
 */
@Data
public class WxLoginRequest {

    @NotBlank
    private String code;

    private String wxAppId;

    private String wxNickName;

    private String wxAvatarUrl;

    private Integer gender;

    private String province;

    private String city;

    private String country;
}
