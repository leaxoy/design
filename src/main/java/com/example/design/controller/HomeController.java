package com.example.design.controller;

import com.example.design.model.resource.User;
import com.example.design.service.impl.CookingService;
import com.example.design.service.impl.MenuService;
import com.example.design.service.impl.UserService;
import com.example.design.util.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    public String home(HttpServletRequest request, Model model) throws Exception {
        redisTemplate.boundValueOps("1").set("123");
        Cookie[] allcookie = request.getCookies();
        if (allcookie == null) {
            String a = redisTemplate.boundValueOps("1").get();
            System.out.println(a);
            return "index";
        }

        List<Cookie> cookies = new ArrayList<>(Arrays.asList(request.getCookies()));
        for (Cookie cookie : cookies) {
            if (Objects.equals(cookie.getName(), "userId")) {
                String value = cookie.getValue();
                User user = UserAuth.auth(value);
                if (user != null) {
                    model.addAttribute("currentUser", user);
                    model.addAttribute("", "");
                    return "index";
                }
            }
        }

        return "index";
    }
}
