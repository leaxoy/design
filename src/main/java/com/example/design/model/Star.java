package com.example.design.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 关注关系模型
 * Created by lxh on 4/14/16.
 */
public class Star {
    @Id
    private Long userId;
    private Long menuId;
    private Long cookingId;
    private Date starDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Date getStarDate() {
        return starDate;
    }

    public void setStarDate(Date starDate) {
        this.starDate = starDate;
    }
}
