package com.quan.util;

import com.quan.Enum.ResultEnum;
import com.quan.entity.Result;

public class ResultUtil {
    public static Result Success(Object data){
        return new Result(ResultEnum.Success.getKey(), ResultEnum.Success.getValue(), data);
    }
    public static Result Success(){
        return Success(null);
    }

    public static Result CustomException(Object data){
        return CustomException(ResultEnum.CustomException.getKey(), ResultEnum.CustomException.getValue(), data);
    }
    public static Result CustomException(Integer status, String msg, Object data){
        return new Result(status, msg, data);
    }
    public static Result CustomException(Integer status, String msg){
        return CustomException(status, msg, null);
    }
    public static Result CustomException(String msg){
        return CustomException(ResultEnum.CustomException.getKey(), msg);
    }
    public static Result CustomException(Integer status){
        return CustomException(status, null);
    }
    public static Result CustomException(){
        return CustomException(ResultEnum.CustomException.getKey(), ResultEnum.CustomException.getValue());
    }
    public static Result SystemException(String msg){
        return new Result(ResultEnum.SystemException.getKey(), ResultEnum.SystemException.getValue(), msg);
    }

    public static Result CallException(){
        return CallException(null);
    }

    public static Result CallException(String message){
        if (message == null) {
            message = "服务器繁忙, 请重试";
        }
        return CustomException(message);
    }
}
