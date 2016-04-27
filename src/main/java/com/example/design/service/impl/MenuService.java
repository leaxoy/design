package com.example.design.service.impl;

import com.example.design.mapper.MenuMapper;
import com.example.design.model.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单的服务接口
 *
 * @author lxh
 * @version 0.1
 */
@Service
public class MenuService {
  @Autowired
  private MenuMapper menuMapper;

  public List<Menu> all() {
    return menuMapper.all();
  }
}
