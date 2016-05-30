package com.example.design.constant;

import org.jetbrains.annotations.Contract;

/**
 * 认证结果 AuthResult 返回值   code 返回信息 message 返回内容 content.
 */
public class AuthResult {
  /**
   * 返回码.
   */
  private int code;

  /**
   * 返回结果描述.
   */
  private String message;

  /**
   * 返回内容.
   */
  private Object content;

  /**
   * @param code    resultCode.
   * @param message resultMessage.
   */
  public AuthResult(int code, String message) {
    this.code = code;
    this.message = message;
    this.content = "";
  }

  /**
   * @param code    resultCode.
   * @param message resultMessage.
   * @param content resultContent.
   */
  public AuthResult(int code, String message, Object content) {
    this.code = code;
    this.message = message;
    this.content = content;
  }

  /**
   * @param status another AuthResultStatus.
   */
  public AuthResult(AuthResultStatus status) {
    this.code = status.getCode();
    this.message = status.getMessage();
    this.content = "";
  }

  /**
   * @param status  another AuthResultStatus.
   * @param content another content.
   */
  public AuthResult(AuthResultStatus status, Object content) {
    this.code = status.getCode();
    this.message = status.getMessage();
    this.content = content;
  }

  @Contract("_ -> !null")
  public static AuthResult ok(Object content) {
    return new AuthResult(AuthResultStatus.SUCCESS, content);
  }

  @Contract(" -> !null")
  public static AuthResult ok() {
    return new AuthResult(AuthResultStatus.SUCCESS);
  }

  @Contract(" -> !null")
  public static AuthResult error(AuthResultStatus error) {
    return new AuthResult(error);
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
