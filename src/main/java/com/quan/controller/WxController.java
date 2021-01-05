package com.quan.controller;

import com.quan.entity.request.WxLoginRequest;
import com.quan.service.UserService;
import com.quan.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public Object getWxUserInfo(@RequestBody @Validated WxLoginRequest wxLoginRequest){

        String result = userService.wxLogin(wxLoginRequest);

        return result;
    }

    @GetMapping("/isLoginState")
    public Object isLoginState(@RequestParam("token") String token){
        try {
            JwtUtil.isTokenExpired(token);
            return true;
        }catch (ExpiredJwtException e){
            return false;
        }
    }
}
