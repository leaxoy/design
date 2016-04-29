package com.example.design.controller;

import com.example.design.authorization.annotation.Authorization;
import com.example.design.authorization.annotation.CurrentUser;
import com.example.design.authorization.manager.impl.RedisTokenManager;
import com.example.design.authorization.model.AuthResult;
import com.example.design.authorization.model.AuthToken;
import com.example.design.constant.AuthResultStatus;
import com.example.design.constant.Role;
import com.example.design.model.User;
import com.example.design.service.impl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户认证的controller Created by lxh on 4/20/16.
 */
@RestController
@RequestMapping("/auth")
public class AuthApi {
  @Autowired
  private UserService userService;

  @Autowired
  private RedisTokenManager tokenManager;

  /**
   * 处理用户查看跟人信息.
   *
   * @param user 当前用户.
   * @return 当前用户信息.
   */
  @CrossOrigin(origins = {"http://localhost:8080"})
  @RequestMapping(method = RequestMethod.GET)
  @Authorization({Role.GUEST, Role.USER, Role.ADMIN})
  public ResponseEntity home(@CurrentUser User user) {
    return new ResponseEntity<>(AuthResult.ok(user), HttpStatus.OK);
  }

  /**
   * 处理用户登录.
   *
   * @param signInForm 用户登录表单.
   * @return token 信息.
   */
  @CrossOrigin(origins = {"http://localhost:8080"})
  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity auth(@RequestBody SignInForm signInForm) {
    Assert.notNull(signInForm.getAccount(), "username can not be empty");
    Assert.notNull(signInForm.getPassword(), "password can not be empty");

    User user = userService.getByAccountName(signInForm.getAccount());
    if (user == null ||  //未注册
            !user.getPassword().equals(signInForm.getPassword())) {  //密码错误
      //提示用户名或密码错误
      return new ResponseEntity<>(
              AuthResult.error(AuthResultStatus.USERNAME_OR_PASSWORD_ERROR), HttpStatus.NOT_FOUND);
    }
    //生成一个token，保存用户登录状态
    AuthToken authToken = tokenManager.createToken(user.getAccount());
    return new ResponseEntity<>(AuthResult.ok(authToken), HttpStatus.OK);
  }

  /**
   * 更新用户信息.
   *
   * @param user    当前用户.
   * @param request 请求参数.
   * @return 修改后的用户信息.
   */
  @CrossOrigin(origins = {"http://localhost:8080"})
  @RequestMapping(method = RequestMethod.PUT)
  @Authorization({Role.USER, Role.ADMIN})
  public ResponseEntity update(@CurrentUser User user, HttpServletRequest request) {
    return new ResponseEntity<>(AuthResult.ok(user), HttpStatus.OK);
  }

  /**
   * 退出登录.
   *
   * @param user 当前用户.
   * @return 请求结果.
   */
  @CrossOrigin(origins = {"http://localhost:8080"})
  @RequestMapping(method = RequestMethod.DELETE)
  @Authorization({Role.USER, Role.ADMIN})
  public ResponseEntity logout(@CurrentUser User user) {
    tokenManager.deleteToken(user.getAccount());
    return new ResponseEntity<>(AuthResult.ok(user), HttpStatus.OK);
  }

  private static class SignInForm {
    private String account;
    private String password;

    public SignInForm() {
    }


    String getAccount() {
      return account;
    }

    String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }
}
