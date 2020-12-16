package com.quan.controller;

import com.quan.entity.request.WxLoginRequest;
import com.quan.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/5
 */
@RestController
@RequestMapping("/wx")
@Slf4j
public class WxController {

    @Resource
    private UserService userService;

    @PostMapping("/getUserInfo")
    public Object getWxUserInfo(@RequestBody @Validated WxLoginRequest wxLoginRequest){

        String result = userService.wxLogin(wxLoginRequest);

        return result;
    }
}
