package com.example.design.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 分享模型 Created by lxh on 4/14/16.
 */
public class Share {
  @Id
  private long shareId;
  private long shareUserId;
  private Date shareDate;
  private String shareType;
  private long itemId;
  private String shareContent;
  private int state;

  public long getShareId() {
    return shareId;
  }

  public void setShareId(long shareId) {
    this.shareId = shareId;
  }

  public long getShareUserId() {
    return shareUserId;
  }

  public void setShareUserId(long shareUserId) {
    this.shareUserId = shareUserId;
  }

  public Date getShareDate() {
    return shareDate;
  }

  public void setShareDate(Date shareDate) {
    this.shareDate = shareDate;
  }


  public String getShareContent() {
    return shareContent;
  }

  public void setShareContent(String shareContent) {
    this.shareContent = shareContent;
  }

  public String getShareType() {
    return shareType;
  }

  public void setShareType(String shareType) {
    this.shareType = shareType;
  }

  public long getItemId() {
    return itemId;
  }

  public void setItemId(long itemId) {
    this.itemId = itemId;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }
}
