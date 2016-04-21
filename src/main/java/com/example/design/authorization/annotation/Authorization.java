package com.example.design.authorization.annotation;

import com.example.design.constant.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在Controller的方法上使用此注解，该方法在映射时会对用户进行身份验证，验证失败返回401错误
 * 也可以直接在Controller上使用，代表该Controller的所有方法均需要身份验证
 * Created by lxh on 4/20/16.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {
    Role[] value() default {Role.USER};
}
