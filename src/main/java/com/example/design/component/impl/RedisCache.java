package com.example.design.component.impl;

import com.example.design.component.Cache;

import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Redis cache.
 *
 * @author lxh
 * @version 0.1
 */
@Component
public class RedisCache implements Cache {

  @Override
  public void sort() {

  }

  @Override
  public Collection get(String key) {
    return null;
  }

  @Override
  public void set(String key, Collection val) {

  }

  @Override
  public void remove(String key) {

  }

  @Override
  public void clean() {

  }

  @Override
  public void append(String key, Collection val) {

  }
}
