package com.example.design.controller;

import com.example.design.authorization.annotation.Authorization;
import com.example.design.authorization.annotation.CurrentUser;
import com.example.design.constant.ResultStatus;
import com.example.design.authorization.model.AuthResult;
import com.example.design.model.User;
import com.example.design.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户认证的controller
 * Created by lxh on 4/20/16.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "auth";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity auth(HttpServletRequest request, @RequestParam String username, @RequestParam String password) {
        Assert.notNull(username, "username can not be empty");
        Assert.notNull(password, "password can not be empty");

        User user = userService.getByAccountName(username);
        if (user == null ||  //未注册
                !user.getPassword().equals(password)) {  //密码错误
            //提示用户名或密码错误
            return new ResponseEntity<>(AuthResult.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR), HttpStatus.NOT_FOUND);
        }
        System.out.println(user.toString());
        //生成一个token，保存用户登录状态
        String name = user.getAccountName();
        request.getSession().setAttribute("userId", name);
        return new ResponseEntity<>(AuthResult.ok(user), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @Authorization
    public ResponseEntity logout(@CurrentUser User user) {
        return new ResponseEntity<>(AuthResult.ok(user), HttpStatus.OK);
    }
}
