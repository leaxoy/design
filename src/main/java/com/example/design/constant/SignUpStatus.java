package com.example.design.constant;

/**
 * 用户注册返回状态.
 *
 * @author lxh
 * @version 0.1
 */
public enum SignUpStatus {
  SUCCESS(100, "成功."),
  UNKNOWN_ERROR(101, "服务器错误,请重新尝试."),
  USER_HAS_EXISTS(102, "该手机号已被注册, 请换一个再试.");

  private int code;
  private String message;

  SignUpStatus(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
