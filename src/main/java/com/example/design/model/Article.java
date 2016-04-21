package com.example.design.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 作品模型 article
 * Created by lxh on 4/14/16.
 */
public class Article {
    @Id
    private Long articleId;
    private String showName;
    private String showPicture;
    private Date showDate;
    private Long userId;
    private String userName;
    private Long cookingId;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getCookingId() {
        return cookingId;
    }

    public void setCookingId(Long cookingId) {
        this.cookingId = cookingId;
    }
}
