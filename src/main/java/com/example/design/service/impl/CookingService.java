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
  CookingLikeMapper cookingLikeMapper;

  /**
   * insert one recipe into  table cooking
   *
   * @param cooking
   * @return
   */
  public int addCooking(Cooking cooking) {
    return cookingMapper.addCooking(cooking);
  }

  /**
   * select one recipe by cookingId
   *
   * @param cookingId
   * @return Cooking
   */
  public Cooking findById(long cookingId) {
    return cookingMapper.findById(cookingId);
  }

  /**
   * UPDATE  one recipe's information except auhtorId,cookingId,state,cookingDate,cookingLike
   *
   * @param cooking
   * @return
   */
  public int updateCooking(Cooking cooking) {
    return cookingMapper.updateCooking(cooking);
  }

  /**
   * set one recipe's state as "1" which means it has been deleted
   *
   * @param cookingId
   * @return
   */
  public int markCookingDetele(long cookingId) {
    return cookingMapper.markCookingDelete(cookingId);
  }

  /**
   * select one user's all recipes
   *
   * @param userId
   * @return List<Cooking>
   */
  public List<Cooking> findAllCookingByUserId(long userId) {
    return cookingMapper.findAllCookingByUserId(userId);
  }

  /**
   * select all cooking by keywords such as ingredient,cookingName, cookingStyleName
   *
   * @param keywords
   * @return List<Cooking>
   */
  public List<Cooking> findAllCookingByKeywords(String keywords) {
    return cookingMapper.findCookingByKerwords(keywords);
  }

  /**
   * select all cooking
   *
   * @return
   */
  public List<Cooking> all() {
    return cookingMapper.all();
  }

  /**
   * if one user click Like,his(her) behavior will be recorded,and set state as 1
   *
   * @param cookingLike
   * @return
   */
  public int addCookingLikeUser(CookingLike cookingLike) {
    return cookingLikeMapper.addCookingLikeUser(cookingLike);
  }

  /**
   * select user's one cooking-like record
   *
   * @param userId
   * @param cookingId
   * @return
   */
  public CookingLike isLike(long userId, long cookingId) {
    return cookingLikeMapper.isLike(userId, cookingId);
  }

  /**
   * delete cookingLike's record
   *
   * @param userId
   * @param cookingId
   * @return
   */
  public int deleteCookingLike(long userId, long cookingId) {
    return cookingLikeMapper.deleteCookingLike(userId, cookingId);
  }

  /**
   * update cooking's cooingLike
   *
   * @param cookingId
   * @param like
   * @return
   */
  public int updateLikeOfCooking(long cookingId, int like) {
    return cookingLikeMapper.updateLikeOfCooking(cookingId, like);
  }

}
