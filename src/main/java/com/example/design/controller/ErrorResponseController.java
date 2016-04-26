package com.example.design.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 错误页面的controller Created by lxh on 4/18/16.
 */
@Controller
public class ErrorResponseController {
  @RequestMapping("unauthorization")
  public String unauth() {
    return "401";
  }

  @RequestMapping("notfound")
  public String notFound() {
    return "404";
  }

  @RequestMapping("unavailable")
  public String unavailable() {
    return "502";
  }
}
