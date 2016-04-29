package com.example.design.model;

import com.example.design.constant.Role;

import org.springframework.data.annotation.Id;

import java.util.Date;


/**
 * 用户模型 Created by lxh on 4/14/16.
 */
public class User {
  @Id
  private long userId;            // 用户ID
  private String account;         // 账户名称
  private String password;        // 用户密码
  private String email;           // 邮箱
  private String nickName;        // 昵称
  private String userPicture;     // 用户头像
  private String name;            // 真实姓名
  private String gender;          // 性别
  private Date birth;             // 出生日期
  private String job;             // 工作
  private String city;            // 居住城市
  private String userIntro;       // 个人介绍
  private Role role;              // 用户权限
  private int state;              // 已废弃

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getUserPicture() {
    return userPicture;
  }

  public void setUserPicture(String userPicture) {
    this.userPicture = userPicture;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Date getBirth() {
    return birth;
  }

  public void setBirth(Date birth) {
    this.birth = birth;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getUserIntro() {
    return userIntro;
  }

  public void setUserIntro(String userIntro) {
    this.userIntro = userIntro;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }
}
