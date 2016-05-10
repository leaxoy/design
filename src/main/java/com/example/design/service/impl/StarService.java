package com.example.design.service.impl;

import com.example.design.mapper.StarMapper;
import com.example.design.model.Star;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 关注关系的服务接口 Created by lxh on 4/20/16.
 */
@Service
@Transactional
public class StarService {

  @Autowired
  private StarMapper starMapper;


  public List<Star> findAll() {
    return starMapper.all();
  }

  public List<Star> findByUserId(long userId) {
    return starMapper.findByUserId(userId);
  }

  public Star findByStarId(long starId) {
    return starMapper.findById(starId);
  }

  public int addStar(Star star) {
    return starMapper.addStar(star);
  }

  public int deleteStar(Star star) {
    return starMapper.deleteStar(star);
  }

  public int deleteStarById(long starId) {
    return starMapper.deleteStarById(starId);
  }
}
