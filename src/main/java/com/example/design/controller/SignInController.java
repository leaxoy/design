package com.example.design.controller;

import com.example.design.model.resource.User;
import com.example.design.service.impl.UserService;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lxh on 4/20/16.
 */
public class SignInController {
    private UserService userService;

    public SignInController(UserService userService) {
        this.userService = userService;
    }

    public String home() {
        return "signin";
    }

    public String signin(HttpServletRequest request, Model model) {
        String phone = request.getParameter("phone");
        String passwd = request.getParameter("passwd");
        User user = userService.getByAccountName(phone);
        if (user != null) {
            model.addAttribute("noSuchUserError", "No such user.");
            return "redirect:/signin";
        }
        return "success";
    }

}
