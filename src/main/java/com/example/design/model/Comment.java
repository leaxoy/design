package com.example.design.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by lxh on 4/14/16.
 */
public class Comment {
    @Id
    private Long commentId;
    private Long userId;
    private String comment;
    private Date commentDate;
    private Long menuId;
    private Long cookingId;
    private Long shodId;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getCookingId() {
        return cookingId;
    }

    public void setCookingId(Long cookingId) {
        this.cookingId = cookingId;
    }

    public Long getShodId() {
        return shodId;
    }

    public void setShodId(Long shodId) {
        this.shodId = shodId;
    }
}
