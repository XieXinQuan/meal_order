package com.quan.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.quan.Enum.ResultEnum;
import com.quan.constant.Constant;
import com.quan.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/5/16
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws SignatureException {

        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        // Token 验证
        String token = request.getHeader(Constant.jwtHeader);
        try{
            Claims claims = JwtUtil.getTokenClaim(token);
            if(claims == null || JwtUtil.isTokenExpired(token)){
                return this.print(response);
            }
        }catch (Exception e){
            return this.print(response);
        }

        return true;
    }

    private boolean print(HttpServletResponse response){
        //session无效，返回resultbean
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            JSONObject res = new JSONObject();
            res.put("status", ResultEnum.GoToLogin.getKey());
            res.put("msg", ResultEnum.GoToLogin.getValue());

            out.write(res.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
