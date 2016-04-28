package com.example.design.service.impl;

import com.example.design.mapper.FriendMapper;
import com.example.design.model.Friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


  /**
   * 建立用户关系.
   *
   * @param userId   用户ID.
   * @param friendId 朋友ID.
   * @return 新建结果.
   */
  public int buildFriendShip(long userId, long friendId) {
    return friendMapper.buildFriendShip(userId, friendId);
  }

  /**
   * 获取朋友.
   *
   * @param id 用户ID
   * @return 朋友列表
   */
  public List<Friend> getByUserId(long id) {
    return friendMapper.getByUserId(id);
  }

  /**
   * 删除朋友关系.
   *
   * @param userId   用户ID
   * @param friendId 朋友ID
   * @return 删除结果
   */
  public int removeFriend(long userId, long friendId) {
    return friendMapper.deleteFriendShip(userId, friendId);
  }
}
