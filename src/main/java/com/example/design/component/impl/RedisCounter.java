package com.example.design.component.impl;

import com.example.design.component.Counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Redis Counter Component.
 *
 * @author lxh
 * @version 0.1
 */
@Component
public class RedisCounter<K extends String, V extends Serializable> implements Counter<K, V> {
  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  @Override
  public boolean incr(K key) {
    return false;
  }

  @Override
  public boolean decr(K key) {
    return false;
  }

  @Override
  public boolean set(K key, V val) {
    redisTemplate.boundValueOps(key).set(val);
    return false;
  }

  @Override
  public V get(K key) {
    return null;
  }

  @Override
  public boolean clear(K key) {
    return false;
  }
}
