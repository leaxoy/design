package com.example.design.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 错误页面的controller Created by lxh on 4/18/16.
 */
@Controller
public class ErrorPageController {
  @RequestMapping("unauthorith")
  public String unauth() {
    return "403";
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
