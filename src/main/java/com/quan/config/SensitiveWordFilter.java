package com.quan.config;

import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.ContextValueFilter;
import com.quan.annotation.ReturnReplace;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/12
 */
public class SensitiveWordFilter implements ContextValueFilter {

    @Override
    public Object process(BeanContext beanContext, Object o, String s, Object o1) {
        if (beanContext == null || o1 == null){
            return o1;
        }
        //替换枚举值
        Field field = beanContext.getField();
        Annotation[] annotations = field.getAnnotations();
        if (annotations == null || annotations.length == 0){
            return o1;
        }
        for (Annotation annotation : annotations){
            if (!(annotation instanceof ReturnReplace)){
                continue;
            }
            ReturnReplace returnReplace = (ReturnReplace) annotation;
            Class<?> value = returnReplace.value();
            if (!value.isEnum()){
                continue;
            }
            Object[] enumConstants = value.getEnumConstants();
            if (enumConstants == null || enumConstants.length == 0){
                continue;
            }
            //找到getKey 和 getValue方法
            Method getKey = ReflectionUtils.findMethod(value, "getKey");
            Method getValue = ReflectionUtils.findMethod(value, "getValue");
            for (Object obj : enumConstants){
                Object o2 = ReflectionUtils.invokeMethod(getKey, obj);
                Object o3 = ReflectionUtils.invokeMethod(getValue, obj);
                if (o2 == null || !o2.equals(o1)){
                    continue;
                }
                return o3;
            }
        }
        return o1;
    }
}
