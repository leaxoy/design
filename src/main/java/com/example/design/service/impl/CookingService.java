package com.example.design.service.impl;

import com.example.design.mapper.CookingLikeMapper;
import com.example.design.mapper.CookingMapper;
import com.example.design.model.Cooking;
import com.example.design.model.CookingLike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜谱服务
 *
 * @author lxh
 * @version 0.1
 */
@Service
public class CookingService {
  @Autowired
  CookingMapper cookingMapper;
  @Autowired
  CookingLikeMapper cookingLikeMapper;

  /**
   * insert one recipe into table cooking.
   *
   * @param cooking cooking to be added.
   * @return number of rows affected.
   */
  public int addCooking(Cooking cooking) {
    return cookingMapper.addCooking(cooking);
  }

  /**
   * select one recipe by cookingId.
   *
   * @param cookingId cooking's id.
   * @return Cooking
   */
  public Cooking findById(long cookingId) {
    return cookingMapper.findById(cookingId);
  }

  /**
   * UPDATE  one recipe's information except authorId,cookingId,state,cookingDate,cookingLike.
   *
   * @param cooking cooking to be updated.
   * @return number of rows affected.
   */
  public int updateCooking(Cooking cooking) {
    return cookingMapper.updateCooking(cooking);
  }

  /**
   * set one recipe's state as "1" which means it has been deleted.
   *
   * @param cookingId cooking's id.
   * @return number of rows affected.
   */
  public int markCookingDelete(long cookingId) {
    return cookingMapper.markCookingDelete(cookingId);
  }

  /**
   * select one user's all recipes.
   *
   * @param userId user'id.
   * @return List
   */
  public List<Cooking> findAllCookingByUserId(long userId) {
    return cookingMapper.findAllCookingByUserId(userId);
  }

  /**
   * select all cooking by keywords such as ingredient,cookingName, cookingStyleName.
   *
   * @param keywords keyword...
   * @return List
   */
  public List<Cooking> findAllCookingByKeywords(String keywords) {
    return cookingMapper.findCookingByKeywords(keywords);
  }

  /**
   * select all cooking.
   *
   * @return all cookings.
   */
  public List<Cooking> all() {
    return cookingMapper.all();
  }

  /**
   * if one user click Like,his(her) behavior will be recorded,and set state as 1.
   *
   * @param cookingLike cooking-like form.
   * @return number of rows affected.
   */
  public int addCookingLikeUser(CookingLike cookingLike) {
    return cookingLikeMapper.addCookingLikeUser(cookingLike);
  }

  /**
   * delete cookingLike's record.
   *
   * @param userId    user'is.
   * @param cookingId cooking.id
   * @return number of rows affected.
   */
  public int deleteCookingLike(long userId, long cookingId) {
    return cookingLikeMapper.deleteCookingLike(userId, cookingId);
  }

  public int likeNumIncr(long cookingId) {
    return cookingMapper.likeNumIncr(cookingId);
  }

  public int likeNumDecr(long cookingId) {
    return cookingMapper.likeNumDecr(cookingId);
  }

}
