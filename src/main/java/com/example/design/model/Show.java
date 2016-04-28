package com.example.design.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 作品模型 article Created by lxh on 4/14/16.
 */
public class Show {
  @Id
  private long showId;
  private String showIntro;
  private String showPicture;
  private Date showDate;
  private long authorId;
  private long cookingId;
  private int showLikeNum;
  private int state;

  public long getShowId() {
    return showId;
  }

  public void setShowId(long showId) {
    this.showId = showId;
  }

  public String getShowIntro() {
    return showIntro;
  }

  public void setShowIntro(String showIntro) {
    this.showIntro = showIntro;
  }

  public String getShowPicture() {
    return showPicture;
  }

  public void setShowPicture(String showPicture) {
    this.showPicture = showPicture;
  }

  public Date getShowDate() {
    return showDate;
  }

  public void setShowDate(Date showDate) {
    this.showDate = showDate;
  }

  public long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(long authorId) {
    this.authorId = authorId;
  }

  public long getCookingId() {
    return cookingId;
  }

  public void setCookingId(long cookingId) {
    this.cookingId = cookingId;
  }

  public int getShowLikeNum() {
    return showLikeNum;
  }

  public void setShowLikeNum(int showLikeNum) {
    this.showLikeNum = showLikeNum;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }
}
