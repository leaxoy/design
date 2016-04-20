package com.example.design.model.resource;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 留言模型
 * Created by lxh on 4/14/16.
 */
public class Message {
    @Id
    private Long messageId;     // 留言 ID
    private Long messageUserId; // 留言的用户ID
    private Date messageDate;   // 留言日期
    private String message;     // 留言内容
    private Long userId;        // 被留言用户 ID
    private Long shareId;       // 被留言的分享ID
    private Long showId;        // 被留言的作品ID
    private int state;          // 留言状态

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getMessageUserId() {
        return messageUserId;
    }

    public void setMessageUserId(Long messageUserId) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
