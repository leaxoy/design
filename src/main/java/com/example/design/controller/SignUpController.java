package com.example.design.controller;

import com.example.design.constant.Role;
import com.example.design.constant.SignUpResponse;
import com.example.design.constant.SignUpStatus;
import com.example.design.model.User;
import com.example.design.service.impl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户注册接口
 *
 * @author lxh
 * @version 0.1
 */
@RestController
@RequestMapping("register")
public class SignUpController {

  /**
   * 用户注册时,用来查重.
   */
  @Autowired
  private UserService userService;

  /**
   * 新用户注册.
   */
  @CrossOrigin(origins = {"http://localhost:8080"})
  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity signUp(@RequestBody SignUpForm signUpForm) {
    /**
     * 如果账户已存在, 则返回错误.
     */
    if (userService.getByAccountName(signUpForm.getAccount()) != null) {
      return ResponseEntity.ok(SignUpResponse.error(SignUpStatus.USER_HAS_EXISTS));
    }
    /**
     * 保存用户,并返回.
     */
    User user = new User();
    user.setAccount(signUpForm.getAccount());
    user.setPassword(signUpForm.getPassword());
    user.setRole(Role.USER);
    user.setState(0);
    int count = userService.addUser(user);

    if (count == 1) {
      return ResponseEntity.ok(SignUpResponse.ok(SignUpStatus.SUCCESS));
    }

    /**
     * 如果存入数据库时发生错误,返回.
     */
    return ResponseEntity.ok(SignUpResponse.error(SignUpStatus.UNKNOWN_ERROR));
  }

  /**
   * 用户的注册表单.
   */
  private static class SignUpForm {
    /**
     * 账户名称.
     */
    private String account;

    /**
     * 账户的密码.
     */
    private String password;

    public SignUpForm(String account, String password) {
      this.account = account;
      this.password = password;
    }

    public SignUpForm() {
    }

    public String getAccount() {
      return account;
    }

    public String getPassword() {
      return password;
    }
  }

}
