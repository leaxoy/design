package com.example.design.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by lxh on 4/17/16.
 */
@Aspect(value = "com.example.design")
public class TestInterceptor {
    @Pointcut("execution(public * com.example.design.mapper.*.insert*(..))||execution(* com.example.design.mapper.*.add*(..))")
    public void insert() {

    }

    @Before("execution(public * com.example.design.mapper.UserMapper.select*(..))")
    public void find() {

    }
}
