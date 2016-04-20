package com.example.design.controller;

import com.example.design.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lxh on 4/20/16.
 */
@Controller
@RequestMapping("signin")
public class SignInController {
    private UserService userService;

    @Autowired
    public SignInController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("")
    public String home() {
        return "signin";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String signin(HttpServletRequest request, Model model) {
        String phone = request.getParameter("phone");
        String passwd = request.getParameter("passwd");
        if (userService.count(phone, passwd) != 0) {
            return "fail";
        }
        return "success";
    }

}
