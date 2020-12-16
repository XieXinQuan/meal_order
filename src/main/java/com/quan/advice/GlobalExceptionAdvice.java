package com.quan.advice;

import com.alibaba.fastjson.JSON;
import com.quan.Enum.ResultEnum;
import com.quan.entity.Result;
import com.quan.exception.GlobalException;
import com.quan.util.ResultUtil;
import com.quan.util.StringUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/1/11
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @Resource
    HttpServletRequest request;

    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e){
        log.error("There is a serious system exception, please deal with it...");
        log.error("Exception Start...");
        log.error("Exception Type : {}", e.getClass().getName());
        log.error("Exception Case: {}" , e.getMessage());
        log.error("Exception", e);
        log.error("Exception End...");
        return ResultUtil.CustomException("出现错误, 请联系管理员.");
    }

    @ExceptionHandler(value = GlobalException.class)
    public Result exceptionHandler(GlobalException e){
        Integer status = e.getStatus() == null ? ResultEnum.CustomException.getKey() : e.getStatus();
        String msg = e.getMsg();
        if(msg == null){
            for(ResultEnum re : ResultEnum.values()){
                if(re.getKey().equals(status)) {
                    msg = re.getValue();
                    break;
                }
            }
        }
        log.info("Custom Exception Start");
        log.info(JSON.toJSONString(ResultUtil.CustomException(status, msg)));
        log.info("Custom Exception End");
        return ResultUtil.CustomException(status, msg);
    }

    @ExceptionHandler(value = ValidationException.class)
    public Result exceptionHandler(ValidationException e){
        log.info("Valid Exception Start...");
        String msg = StringUtils.validExceptionStr(e.getMessage());
        log.info(msg);
        log.info("Valid Exception End...");
        return ResultUtil.CustomException(msg);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result exceptionHandler(MethodArgumentNotValidException e){
        return this.validDeal(e.getBindingResult());
    }


    @ExceptionHandler(value = BindException.class)
    public Result exceptionHandler(BindException e){
        return this.validDeal(e.getBindingResult());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result exceptionHandler(ConstraintViolationException e){
        log.info("Valid Exception Start...");
        String msg = StringUtils.validExceptionStr(e.getMessage());
        log.info(msg);
        log.info("Valid Exception End...");
        return ResultUtil.CustomException(msg);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Result exceptionHandler(MissingServletRequestParameterException e){
        log.info("MissingServletRequestParameter Exception Start...");
        log.info("Please Check Parameter Is Not Null, Thanks. ");
        log.info("MissingServletRequestParameter Exception  End...");

        return ResultUtil.CustomException(ResultEnum.PasswordIsError.getKey(), "Please Check Parameter Is Not Null, Thanks.");
    }

    @ExceptionHandler(value = JpaSystemException.class)
    public Result exceptionHandler(JpaSystemException e){
        log.info("JpaSystem Exception Start...");
        log.info("Exception Case : {}" , e.getMessage());
        log.info("JpaSystem Exception  End...");

        return ResultUtil.CustomException(ResultEnum.PasswordIsError.getKey(), "Dd Error...");
    }
    @ExceptionHandler(ExpiredJwtException.class)
    public Result exceptionHandler(ExpiredJwtException e){
        log.info("Login Info Expired");
        log.info("Exception Case : {}", e.getMessage());
        return ResultUtil.CustomException(ResultEnum.LoginOverDue.getKey(), ResultEnum.LoginOverDue.getValue());
    }

    @ExceptionHandler(SignatureException.class)
    public Result exceptionHandler(SignatureException e){
        log.info("Login Info Expired");
        log.info("Exception Case : {}", e.getMessage());
        return ResultUtil.CustomException(ResultEnum.LoginOverDue.getKey(), ResultEnum.LoginOverDue.getValue());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result exceptionHandler(HttpRequestMethodNotSupportedException e){

        return ResultUtil.CustomException(ResultEnum.CustomException.getKey(), e.getMessage());
    }

    private Result validDeal(BindingResult bindingResult){
        log.info("Valid Exception Start...");

        List<ObjectError> allErrors = bindingResult.getAllErrors();

        List<String> msgs = allErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        String msg = org.apache.commons.lang.StringUtils.join(msgs, ", ");
        log.error(msg);
        log.info("Valid Exception End...");
        return ResultUtil.CustomException(msg);
    }

}
