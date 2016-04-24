package com.example.design.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 菜谱模型 Created by lxh on 4/14/16.
 */
public class Cooking {
  @Id
  private Long cookingId;
  private String cookingName;
  private String cookingStyle;
  private Integer cookingStyleID;
  private Date cookingDate;
  private String author;
  private String cookingPicture;
  private String cookingIntro;
  private String tips;
  private Integer cookingLike;
  private String step;
  private String ingredient;
  private Boolean state;

  public Long getCookingId() {
    return cookingId;
  }

  public void setCookingId(Long cookingId) {
    this.cookingId = cookingId;
  }

  public String getCookingName() {
    return cookingName;
  }

  public void setCookingName(String cookingName) {
    this.cookingName = cookingName;
  }

  public String getCookingStyle() {
    return cookingStyle;
  }

  public void setCookingStyle(String cookingStyle) {
    this.cookingStyle = cookingStyle;
  }

  public Integer getCookingStyleID() {
    return cookingStyleID;
  }

  public void setCookingStyleID(Integer cookingStyleID) {
    this.cookingStyleID = cookingStyleID;
  }

  public Date getCookingDate() {
    return cookingDate;
  }

  public void setCookingDate(Date cookingDate) {
    this.cookingDate = cookingDate;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getCookingPicture() {
    return cookingPicture;
  }

  public void setCookingPicture(String cookingPicture) {
    this.cookingPicture = cookingPicture;
  }

  public String getCookingIntro() {
    return cookingIntro;
  }

  public void setCookingIntro(String cookingIntro) {
    this.cookingIntro = cookingIntro;
  }

  public String getTips() {
    return tips;
  }

  public void setTips(String tips) {
    this.tips = tips;
  }

  public Integer getCookingLike() {
    return cookingLike;
  }

  public void setCookingLike(Integer cookingLike) {
    this.cookingLike = cookingLike;
  }

  public String getStep() {
    return step;
  }

  public void setStep(String step) {
    this.step = step;
  }

  public String getIngredient() {
    return ingredient;
  }

  public void setIngredient(String ingredient) {
    this.ingredient = ingredient;
  }

  public Boolean getState() {
    return state;
  }

  public void setState(Boolean state) {
    this.state = state;
  }

}
