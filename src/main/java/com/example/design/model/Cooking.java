package com.example.design.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * 菜谱模型 Created by lxh on 4/14/16.
 */
@Document(indexName = "design", type = "cooking")
public class Cooking {
  @Id
  private long cookingId;
  @Field(type = FieldType.String, index = FieldIndex.analyzed)
  private String cookingName;
  @Field(type = FieldType.String, index = FieldIndex.analyzed)
  private String cookingStyle;
  private Date cookingDate;
  private long authorId;
  private String cookingPicture;
  @Field(type = FieldType.String, index = FieldIndex.analyzed)
  private String cookingIntro;
  @Field(type = FieldType.String, index = FieldIndex.analyzed)
  private String tips;
  private int cookingLikeNum;
  @Field(type = FieldType.String, index = FieldIndex.analyzed)
  private String step;
  @Field(type = FieldType.String, index = FieldIndex.analyzed)
  private String ingredient;
  private int state;

  public Cooking() {
  }

  public long getCookingId() {
    return cookingId;
  }

  public void setCookingId(long cookingId) {
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

  public Date getCookingDate() {
    return cookingDate;
  }

  public void setCookingDate(Date cookingDate) {
    this.cookingDate = cookingDate;
  }

  public long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(long authorId) {
    this.authorId = authorId;
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

  public int getCookingLikeNum() {
    return cookingLikeNum;
  }

  public void setCookingLikeNum(int cookingLikeNum) {
    this.cookingLikeNum = cookingLikeNum;
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

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

}
