package com.example.design.exception;

/**
 * 方法不支持当前环境的异常，主要跟一些配置参数有关
 * Created by lxh on 4/20/16.
 */
public class MethodNotSupportException extends RuntimeException {
    public MethodNotSupportException(String message) {
        super(message);
    }
}
