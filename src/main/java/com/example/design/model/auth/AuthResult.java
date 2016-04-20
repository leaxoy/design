package com.example.design.model.auth;

import com.example.design.constant.ResultStatus;

/**
 * 认证结果 AuthResult
 * 返回值   code
 * 返回信息 message
 * 返回内容 content
 * <p>
 * Created by lxh on 4/20/16.
 * </p>
 */
public class AuthResult {
    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    /**
     * 返回内容
     */
    private Object content;

    public AuthResult(int code, String message) {
        this.code = code;
        this.message = message;
        this.content = "";
    }

    public AuthResult(int code, String message, Object content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public AuthResult(ResultStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.content = "";
    }

    public AuthResult(ResultStatus status, Object content) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.content = content;
    }

    public static AuthResult ok(Object content) {
        return new AuthResult(ResultStatus.SUCCESS, content);
    }

    public static AuthResult ok() {
        return new AuthResult(ResultStatus.SUCCESS);
    }

    public static AuthResult error(ResultStatus error) {
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
