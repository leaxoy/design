package com.example.design.service.impl;

import com.example.design.mapper.FriendMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户关系的服务 Created by lxh on 4/20/16.
 */
@Service
public class FriendService {
  @Autowired
  private FriendMapper mapper;
}
