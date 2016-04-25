package com.example.design.service.impl;

import com.example.design.mapper.ShowMapper;
import com.example.design.model.Show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户作品服务 Created by lxh on 4/14/16.
 */
@Service
public class ShowService {
  @Autowired
  private ShowMapper showMapper;


  public List<Show> all() {
    return showMapper.getAll();
  }


  public Show id(long id) {
    return null;
  }

  public List<Show> findByUserId(long userId) {
    return null;
  }

  public List<Show> top10() {
    return null;
  }

  public int update(Show show) {
    return 0;
  }

  public int add(Show show) {
    return 0;
  }

  public int delete(Show show) {
    return 0;
  }
}
