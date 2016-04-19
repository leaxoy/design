package com.example.design.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by lxh on 4/18/16.
 */
@Aspect(value = "log")
public aspect Log {

    @Pointcut(value = "")
    public void logBefore() {

    }
}
