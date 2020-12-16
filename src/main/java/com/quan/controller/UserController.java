package com.quan.controller;

import com.quan.entity.request.LoginRequest;
import com.quan.entity.request.SearchUserRequest;
import com.quan.service.UserService;
import com.quan.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/11/14
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Object login(@RequestBody @Validated LoginRequest loginRequest){
        Integer uId = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return JwtUtil.createToken(uId);
    }

    @PostMapping("/register")
    public Object register(@RequestParam("username") @Size(max = 5, min = 1, message = "用户名长度在1~5位") String username,
                           @RequestParam("password") @Size(max = 18, min = 6, message = "密码的长度为6~18位") String password,
                           @RequestParam("email") @Email(message = "邮箱地址不合法") String email,
                           @RequestParam("code") @Size(max = 4, min = 4, message = "验证码的长度为4位") String code){
        Integer uId = userService.registerUser(username, email, password, code);
        return JwtUtil.createToken(uId);
    }


    @PostMapping("/code")
    public Object code(@RequestParam("email") @Email(message = "邮箱地址不合法") String email) {
        userService.code(email);
        return "发送成功";
    }

    @GetMapping("/info")
    public Object info(){
        return userService.userInfo();
    }

    @GetMapping("/searchUserByName")
    public Object searchUserByName(@Validated SearchUserRequest searchUserByName){
        return userService.searchUserByName(searchUserByName.getName());
    }

}
