package com.example.design.service.impl;

import com.example.design.mapper.FriendMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户关系的服务
 *
 * @author lxh
 * @version 0.1
 */
@Service
public class FriendService {
  @Autowired
  private FriendMapper friendMapper;
}
