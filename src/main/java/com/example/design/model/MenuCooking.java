package com.example.design.model;

import org.springframework.data.annotation.Id;

/**
 * Menu cooking model.
 *
 * @author lxh
 * @version 0.1
 */
public class MenuCooking {
  @Id
  private long menuId;
  @Id
  private long cookingId;


  public long getMenuId() {
    return menuId;
  }

  public void setMenuId(long menuId) {
    this.menuId = menuId;
  }

  public long getCookingId() {
    return cookingId;
  }

  public void setCookingId(long cookingId) {
    this.cookingId = cookingId;
  }
}
