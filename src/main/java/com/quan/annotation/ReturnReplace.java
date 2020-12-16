package com.quan.annotation;

import java.lang.annotation.*;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/13
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ReturnReplace {
    Class<?> value() default Void.class;
}
