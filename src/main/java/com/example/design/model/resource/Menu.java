package com.example.design.model.resource;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by lxh on 4/14/16.
 */
public class Menu {
    @Id
    private Long menuId;
    private String menuName;
    private String menuPicture;
    private String cookingName;
    private String cookingId;
    private Integer menuLike;
    private Date menuDate;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
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

    public String getCookingName() {
        return cookingName;
    }

    public void setCookingName(String cookingName) {
        this.cookingName = cookingName;
    }

    public String getCookingId() {
        return cookingId;
    }

    public void setCookingId(String cookingId) {
        this.cookingId = cookingId;
    }

    public Integer getMenuLike() {
        return menuLike;
    }

    public void setMenuLike(Integer menuLike) {
        this.menuLike = menuLike;
    }

    public Date getMenuDate() {
        return menuDate;
    }

    public void setMenuDate(Date menuDate) {
        this.menuDate = menuDate;
    }
}
