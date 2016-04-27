package com.example.design.constant;

import java.util.Date;

/**
 * Created by lxh on 16/4/27.
 */
public class ResponseData {

  private int code;
  private String message;
  private String event;
  private String serveTime;
  private Object content;


  public ResponseData(int code, String message, String event, String serveTime, Object content) {
    this.code = code;
    this.message = message;
    this.event = event;
    this.serveTime = serveTime;
    this.content = content;
  }

  public ResponseData(int code, String event, String message) {
    this(code, message, event, new Date().toString(), null);
  }

  public ResponseData(int code, String event, String message, Object content) {
    this(code, message, event, new Date().toString(), content);
  }

  public ResponseData(ResponseData responseData) {
    this.code = responseData.code;
    this.message = responseData.message;
    this.content = responseData.content;
  }

  public static ResponseData ok(int code, String message, String event, Object content) {
    return new ResponseData(code, message, event, content);
  }

  public static ResponseData ok(String event,  Object content) {
    return new ResponseData(100, "success", event, content);
  }
}
