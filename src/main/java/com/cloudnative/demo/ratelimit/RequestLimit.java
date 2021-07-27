package com.cloudnative.demo.ratelimit;

import java.lang.annotation.*;

/**
 * 接口限流注解
 * @author fguohao
 * @date 2021/07/27
 */
 @Target(ElementType.METHOD)
 @Retention(RetentionPolicy.RUNTIME)
 @Documented
public @interface RequestLimit {

    String value() default "" ;

    String methodName() default "" ;

    long count() default 100;

}
