package com.example.design.component.model;

import com.example.design.constant.Role;

import java.io.Serializable;

/**
 * Created by lxh on 16/5/10.
 */
public class TokenModel implements Serializable {
  private String account;
  private String nickName;
  private Role role;
  private String content;

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String payload() {
    return this.account + this.nickName + ":" + this.role.name();
  }
}
