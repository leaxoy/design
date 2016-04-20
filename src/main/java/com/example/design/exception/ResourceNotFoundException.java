package com.example.design.exception;

/**
 * Created by lxh on 4/20/16.
 */
public class ResourceNotFoundException extends org.springframework.data.rest.webmvc.ResourceNotFoundException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
