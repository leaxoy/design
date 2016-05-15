package com.example.design.aspect;

import com.example.design.service.support.ClickCountService;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Click aspect.
 *
 * @author lxh
 * @version 0.1
 */
@Aspect
@Component
public class ClickAspect {
  private final Logger logger = LoggerFactory.getLogger(ClickAspect.class);
  @Autowired
  private ClickCountService clickCountService;

  @Pointcut("execution(* com.example.design.controller.restapi..*.*(..))")
  void apiCut() {
  }

  @Before("apiCut())")
  public void doCountServiceBeforeRequest(JoinPoint joinPoint) {

  }

  @After("apiCut()")
  public void doCountServiceAfterRequest(JoinPoint joinPoint) {

  }

  @Around("apiCut()")
  public Object doCountServiceInsideRequest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    return proceedingJoinPoint.proceed();
  }

  @AfterReturning("apiCut()")
  public void doCountServiceAfterReturn(JoinPoint joinPoint) {

  }

  @AfterThrowing("apiCut()")
  public void doCountServiceAfterThrow(JoinPoint joinPoint) {

  }
}
