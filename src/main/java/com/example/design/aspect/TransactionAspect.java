package com.example.design.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 事务AOP
 * Created by lxh on 4/22/16.
 */
@Aspect
public class TransactionAspect {

    @Before("@annotation(com.example.design.authorization.annotation.Authorization)")
    public void beginTransaction(JoinPoint joinPoint) {

    }
}
