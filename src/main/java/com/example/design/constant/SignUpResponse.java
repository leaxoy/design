package com.example.design.constant;

/**
 * Created by lxh on 16/4/26.
 */
public class SignUpResponse {
  private int code;
  private String message;
  private Object content;


  public SignUpResponse(int code, String message, Object content) {
    this.code = code;
    this.message = message;
    this.content = content;
  }

  public SignUpResponse(int code, String message) {
    this.code = code;
    this.message = message;
    this.content = "";
  }

  public SignUpResponse(SignUpStatus status) {
    this.code = status.getCode();
    this.message = status.getMessage();
    this.content = "";
  }

  public SignUpResponse(SignUpStatus status, Object content) {
    this.code = status.getCode();
    this.message = status.getMessage();
    this.content = content;
  }

  public static SignUpResponse ok() {
    return new SignUpResponse(SignUpStatus.SUCCESS);
  }

  public static SignUpResponse ok(Object content) {
    return new SignUpResponse(SignUpStatus.SUCCESS, content);
  }

  public static SignUpResponse error(SignUpStatus error) {
    return new SignUpResponse(error);
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public Object getContent() {
    return content;
  }
}
