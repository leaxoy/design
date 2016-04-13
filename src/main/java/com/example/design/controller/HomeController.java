package com.example.design.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lxh on 4/13/16.
 */
@Controller
@RequestMapping("")
public class HomeController {
    @RequestMapping()
    public String home() {
        return "index";
    }
}
