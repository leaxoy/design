package com.example.design.model;

import org.springframework.data.annotation.Id;

/**
 * Show like model.
 *
 * @author lxh
 * @version 0.1
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
