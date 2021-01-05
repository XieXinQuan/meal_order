package com.quan.entity.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/5
 */
@Data
public class WxLoginRequest {

    @NotNull(message = "code 不能为空")
    private String code;

    @NotNull(message = "wxNickName 不能为空")
    private String wxNickName;

    @NotNull(message = "wxAvatarUrl 不能为空")
    private String wxAvatarUrl;

    @NotNull(message = "gender 不能为空")
    private Integer gender;

    @NotNull(message = "province 不能为空")
    private String province;

    @NotNull(message = "city 不能为空")
    private String city;

    @NotNull(message = "country 不能为空")
    private String country;
}
