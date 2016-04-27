package com.example.design.service.impl;

import com.example.design.mapper.CookingMapper;
import com.example.design.model.Cooking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜谱服务
 *
 * @author lxh
 * @version 0.1
 */
@Service
public class CookingService {
  @Autowired
  private CookingMapper cookingMapper;

  public List<Cooking> all() {
    return null;
  }

  public Cooking id(long id) {
    return null;
  }

  public List<Cooking> findByUserId(long userId) {
    return null;
  }
}
