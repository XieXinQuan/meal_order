package com.quan.entity.request;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/12
 */
@Data
public class LoginRequest {


    @Size(max = 5, min = 1, message = "用户名长度在1~5位")
    private String username;


    @Size(max = 18, min = 6, message = "密码的长度为6~18位")
    private String password;
}
