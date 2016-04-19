package com.example.design.controller;

import com.example.design.service.ArticleMapper;
import com.example.design.service.CommentService;
import com.example.design.service.MessageService;
import com.example.design.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("profile")
// localhost:3000/profile/**
public class UserProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ArticleMapper articleService;
    @Autowired
    private CommentService commentService;

    @RequestMapping()
    public String home(Model model) {
        model.addAttribute("title", "Profile");
        return "profile";
    }

    // localhost:3000/profile/edit
    @RequestMapping("edit")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String edit() {
        return "profile/edit";
    }

    // localhost:3000/profile/message
    @RequestMapping("message")
    public String message() {
        return "profile/message";
    }

    // localhost:3000/profile/edit
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String change(HttpServletRequest request) {
        List<Cookie> cookies = Arrays.asList(request.getCookies());
        return "redirect:/profile";
    }
}
