package com.example.design.controller;

import com.example.design.model.resource.User;
import com.example.design.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 注册新用户的controller.
 * Created by lxh on 4/20/16.
 */
@Controller
@RequestMapping("signup")
public class SignUpController {

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String home(Model model) {
        return "signup";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String signup(HttpServletRequest request, Model model) {
        String phone = request.getParameter("phone");
        User user = userService.getByAccountName(phone);
        if (user != null) {
            model.addAttribute("exist", "该手机号已被使用,请换一个");
            return "signup";
        }
        return "redirect:/";
    }

}
