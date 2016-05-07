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


  @Before("execution(* com.example.design.controller.restapi..*.*(..))")
  public void doCountServiceBeforeRequest(JoinPoint joinPoint) {

  }

  @After("execution(* com.example.design.controller.restapi..*.*(..))")
  public void doCountServiceAfterRequest(JoinPoint joinPoint) {

  }

  @Around("execution(* com.example.design.controller.restapi..*.*(..))")
  public Object doCountServiceInsideRequest(ProceedingJoinPoint proceedingJoinPoint) {
    return null;
  }

  @AfterReturning("execution(* com.example.design.controller.restapi..*.*(..))")
  public void doCountServiceAfterReturn(JoinPoint joinPoint) {

  }

  @AfterThrowing("execution(* com.example.design.controller.restapi..*.*(..))")
  public void doCountServiceAfterThrow(JoinPoint joinPoint) {

  }
}
