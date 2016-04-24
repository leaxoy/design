package com.example.design.controller;

import com.example.design.service.impl.CookingService;
import com.example.design.service.impl.MenuService;
import com.example.design.service.impl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 首页的controller Created by lxh on 4/13/16.
 */
@RestController
@RequestMapping("")
public class HomeController {

  @Autowired
  private UserService userService;
  @Autowired
  private MenuService menuService;
  @Autowired
  private CookingService cookingService;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity home(HttpServletRequest request, Model model) throws Exception {
    return ResponseEntity.ok("Hello, world");
  }
}
