package com.example.design.exception;

/**
 * 未认证时发生的异常.
 * Created by lxh on 4/21/16.
 */
public class NotAuthorizationException extends Exception {
    public NotAuthorizationException(String meesgae) {
        super(meesgae);
    }
}
