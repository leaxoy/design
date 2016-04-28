package com.example.design.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 菜单模型 Created by lxh on 4/14/16.
 */
public class Menu {
  @Id
  private long menuId;
  private String menuName;
  private String menuPicture;
  private int menuLikeNum;
  private long authorId;
  private Date menuDate;
  private int state;

  public long getMenuId() {
    return menuId;
  }

  public void setMenuId(long menuId) {
    this.menuId = menuId;
  }

  public String getMenuName() {
    return menuName;
  }

  public void setMenuName(String menuName) {
    this.menuName = menuName;
  }

  public String getMenuPicture() {
    return menuPicture;
  }

  public void setMenuPicture(String menuPicture) {
    this.menuPicture = menuPicture;
  }

  public int getMenuLike() {
    return menuLikeNum;
  }

  public void setMenuLike(int menuLikeNum) {
    this.menuLikeNum = menuLikeNum;
  }

  public Date getMenuDate() {
    return menuDate;
  }

  public void setMenuDate(Date menuDate) {
    this.menuDate = menuDate;
  }

  public long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(long authorId) {
    this.authorId = authorId;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }
}
