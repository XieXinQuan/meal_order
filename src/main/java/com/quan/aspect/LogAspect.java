package com.quan.aspect;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.quan.Enum.ResultEnum;
import com.quan.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/1/11
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 切入点描述 这个是controller包的切入点
     */
    @Pointcut("execution(public * com.quan.controller..*.*(..))")
    public void controllerLog(){}

    @Before("controllerLog()")
    public void logBeforeController(JoinPoint joinPoint) {
        //这个RequestContextHolder是Springmvc提供来获得请求的东西
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();

        // 记录下请求内容
        log.info("Request Type : {}, URL : {}", request.getMethod(), request.getRequestURL().toString());
        log.info("Request Remote IP : {}", request.getRemoteAddr());
        List<String> ignore = Lists.newArrayList("/file/upload");
        if (!ignore.contains(request.getServletPath())) {
            log.info("Request Parameter : {}", JSON.toJSONString(joinPoint.getArgs()));
        }
    }
    @AfterReturning(returning = "returnOb", pointcut = "controllerLog()")
    public void doAfterReturning(Object returnOb) {
        log.info("Request Success Return Data : {}", returnOb);
    }
    @Around("controllerLog()")
    public Object Around(ProceedingJoinPoint pjp) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        long endTime = System.currentTimeMillis();

        log.info("Total Time : {}ms.", (endTime - startTime));
        RestController annotation = pjp.getTarget().getClass().getAnnotation(RestController.class);
        if(annotation != null) {
            return new Result(ResultEnum.Success.getKey(), ResultEnum.Success.getValue(), proceed);
        }
        return proceed;

    }
}
