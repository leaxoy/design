package com.example.design.model;

import org.springframework.data.annotation.Id;

/**
 * Created by lxh on 16/4/25.
 */
public class ShowLike {
  @Id
  private long showId;
  @Id
  private long userId;
  private int state;


  public long getShowId() {
    return showId;
  }

  public void setShowId(long showId) {
    this.showId = showId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }
}
