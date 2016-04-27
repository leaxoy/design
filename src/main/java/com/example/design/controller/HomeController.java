package com.example.design.controller;

import com.example.design.service.impl.CookingService;
import com.example.design.service.impl.MenuService;
import com.example.design.service.impl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
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

  @CrossOrigin(origins = {"http://localhost:8080"})
  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity home(HttpServletRequest request, Model model) throws Exception {
    return ResponseEntity.ok("Hello, world");
  }

  @CrossOrigin(origins = {"http://localhost:8080"})
  @RequestMapping(value = "/greeting", method = RequestMethod.GET)
  public ResponseEntity greeting() {
    Greeting greeting = new Greeting(1, "Hello,world");
    return ResponseEntity.ok().body(greeting);
  }

  private static class Greeting {
    private int id;
    private String content;

    public Greeting(int id, String content) {
      this.id = id;
      this.content = content;
    }

    public int getId() {
      return id;
    }

    public String getContent() {
      return content;
    }
  }
}
