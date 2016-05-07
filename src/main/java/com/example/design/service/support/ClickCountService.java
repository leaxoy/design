package com.example.design.service.support;

import com.example.design.component.impl.RedisCounter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Click Counter Service.
 *
 * @author lxh
 * @version 0.1
 */
@Service
public class ClickCountService {
  @Autowired
  private RedisCounter<String, String> redisCounter;

  public boolean incr(String key) {
    return redisCounter.incr(key);
  }

  public boolean decr(String key) {
    return redisCounter.decr(key);
  }

  public boolean set(String key, String val) {
    return redisCounter.set(key, val);
  }

  public String get(String key) {
    return redisCounter.get(key);
  }

  public boolean clear(String key) {
    return redisCounter.clear(key);
  }
}
