package com.example.design.exception;

/**
 * 某些资源不存在时发生的异常. Created by lxh on 4/20/16.
 */
public class ResourceNotFoundException
        extends org.springframework.data.rest.webmvc.ResourceNotFoundException {
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
