package com.example.design.controller;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lxh on 4/20/16.
 */
public class BaseController {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected Model model;

    protected String redirect(String url) {
        return "redirect:/" + url;
    }

}
