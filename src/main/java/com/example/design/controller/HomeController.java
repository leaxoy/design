package com.example.design.controller;

import com.example.design.authorization.annotation.Authorization;
import com.example.design.constant.UserRole;
import com.example.design.service.impl.CookingService;
import com.example.design.service.impl.MenuService;
import com.example.design.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 首页的controller
 * Created by lxh on 4/13/16.
 */
@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private CookingService cookingService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Authorization({UserRole.GUEST, UserRole.GUEST})
    public String home(HttpServletRequest request, Model model) throws Exception {
        return "index";
    }
}
