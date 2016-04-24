package com.example.design.controller;

import com.example.design.service.impl.ArticleService;
import com.example.design.service.impl.CommentService;
import com.example.design.service.impl.MessageService;
import com.example.design.service.impl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("profile")
// localhost:3000/profile/**
public class UserProfileController {

  private UserService userService;
  private MessageService messageService;
  private ArticleService articleService;
  private CommentService commentService;

  /**
   * @param userService1    userService.
   * @param messageService1 messageService.
   * @param articleService1 articleService.
   * @param commentService1 commentService.
   */
  @Autowired
  public UserProfileController(
          UserService userService1,
          MessageService messageService1,
          ArticleService articleService1,
          CommentService commentService1) {
    this.userService = userService1;
    this.messageService = messageService1;
    this.articleService = articleService1;
    this.commentService = commentService1;
  }

  // localhost:3000/profile
  // :GET
  @PreAuthorize("hasRole('USER')")
  @RequestMapping()
  public String home(Model model) {
    model.addAttribute("title", "Profile");
    return "profile/index";
  }

  // localhost:3000/profile/edit
  // :GET
  @RequestMapping("edit")
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  public String edit() {
    return "profile/edit";
  }

  // localhost:3000/profile/message
  // :GET
  @RequestMapping("message")
  public String message() {
    return "profile/message";
  }

  // localhost:3000/profile/edit
  // :POST
  @RequestMapping(value = "edit", method = RequestMethod.POST)
  public String change(HttpServletRequest request) {
    return "redirect:/profile";
  }
}
