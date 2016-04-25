package com.example.design.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 关注关系模型 Created by lxh on 4/14/16.
 */
public class Star {
  @Id
  private long starId;
  private long userId;
  private String starType;
  private String itemId;
  private int state;
  private Date starDate;

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public Date getStarDate() {
    return starDate;
  }

  public void setStarDate(Date starDate) {
    this.starDate = starDate;
  }

  public long getStarId() {
    return starId;
  }

  public void setStarId(long starId) {
    this.starId = starId;
  }

  public String getStarType() {
    return starType;
  }

  public void setStarType(String starType) {
    this.starType = starType;
  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }
}
