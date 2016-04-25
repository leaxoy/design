package com.example.design.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 留言模型 Created by lxh on 4/14/16.
 */
public class Message {
  @Id
  private long messageId;     // 留言 ID
  private long messageUserId; // 留言的用户ID
  private Date messageDate;   // 留言日期
  private String message;     // 留言内容
  private long userId;        // 被留言用户 ID
  private long shareId;       // 被留言的分享ID
  private long showId;        // 被留言的作品ID
  private int state;        // 留言状态

  public long getMessageId() {
    return messageId;
  }

  public void setMessageId(long messageId) {
    this.messageId = messageId;
  }

  public long getMessageUserId() {
    return messageUserId;
  }

  public void setMessageUserId(long messageUserId) {
    this.messageUserId = messageUserId;
  }

  public Date getMessageDate() {
    return messageDate;
  }

  public void setMessageDate(Date messageDate) {
    this.messageDate = messageDate;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getShareId() {
    return shareId;
  }

  public void setShareId(long shareId) {
    this.shareId = shareId;
  }

  public long getShowId() {
    return showId;
  }

  public void setShowId(long showId) {
    this.showId = showId;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }
}
