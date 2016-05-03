package com.example.design.model;

import org.springframework.data.annotation.Id;

/**
 * Menu like model.
 *
 * @author lxh
 * @version 0.1
 */
public class MenuLike {
  @Id
  private long menuId;
  @Id
  private long userId;
  private int state;

  public long getMenuId() {
    return menuId;
  }

  public void setMenuId(long menuId) {
    this.menuId = menuId;
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
