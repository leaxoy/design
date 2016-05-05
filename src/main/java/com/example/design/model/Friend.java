package com.example.design.model;

import org.springframework.data.annotation.Id;

/**
 * 用户关系模型.
 *
 * @author lxh
 * @version 0.1
 */
public class Friend {
  @Id
  private long userId;
  private long friendId;

  public long getFriendId() {
    return friendId;
  }

  public void setFriendId(long friendId) {
    this.friendId = friendId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }
}
