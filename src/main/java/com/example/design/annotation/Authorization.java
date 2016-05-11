package com.example.design.annotation;

import com.example.design.constant.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在Controller的方法上使用此注解，该方法在映射时会对用户进行身份验证，验证失败返回401错误.
 *
 * 也可以直接在Controller上使用，代表该Controller的所有方法均需要身份验证
 *
 * @author lxh
 * @version 0.1
 * @see java.lang.annotation.Annotation
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {
  /**
   * @return ROLES has permission to access this method.
   */
  Role[] value() default {Role.USER};
}
