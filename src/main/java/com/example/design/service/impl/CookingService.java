package com.example.design.service.impl;

import com.example.design.mapper.CookingLikeMapper;
import com.example.design.mapper.CookingMapper;
import com.example.design.model.Cooking;
import com.example.design.model.CookingLike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜谱服务
 *
 * @author lxh
 * @version 0.1
 */
@Service
@Transactional
public class CookingService {
  @Autowired
  private CookingMapper cookingMapper;
  @Autowired
  private CookingLikeMapper cookingLikeMapper;

  /**
   * insert one recipe into table cooking.
   *
   * @param cooking cooking to be added.
   * @return number of rows affected.
   */
  public int addCooking(Cooking cooking) {
    return cookingMapper.add(cooking);
  }

  /**
   * select one recipe by cookingId.
   *
   * @param cookingId cooking's findById.
   * @return Cooking
   */
  public Cooking findById(long cookingId) {
    return cookingMapper.findById(cookingId);
  }

  /**
   * get top 6 by like number.
   *
   * @return cooking list.
   */
  public List<Cooking> top6() {
    return cookingMapper.top6();
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
   * @param cookingId cooking's findById.
   * @return number of rows affected.
   */
  public int markCookingDelete(long cookingId) {
    return cookingMapper.markDeleted(cookingId);
  }

  /**
   * select one user's findAll recipes.
   *
   * @param userId user'findById.
   * @return List
   */
  public List<Cooking> findAllCookingByUserId(long userId) {
    return cookingMapper.findByUserId(userId);
  }

  /**
   * select findAll cooking by keywords such as ingredient,cookingName, cookingStyleName.
   *
   * @param keywords keyword...
   * @return List
   */
  public List<Cooking> findAllCookingByKeywords(String keywords) {
    return cookingMapper.findByKeyword(keywords);
  }

  /**
   * select findAll cooking.
   *
   * @return findAll cookings.
   */
  public List<Cooking> all() {
    return cookingMapper.findAll();
  }

  /**
   * if one user click Like,his(her) behavior will be recorded,and set state as 1.
   *
   * @param cookingLike cooking-like form.
   * @return number of rows affected.
   */
  public int addCookingLikeUser(CookingLike cookingLike) {
    return cookingLikeMapper.add(cookingLike);
  }

  /**
   * delete cookingLike's record.
   *
   * @param userId    user'is.
   * @param cookingId cooking.findById
   * @return number of rows affected.
   */
  public int deleteCookingLike(long userId, long cookingId) {
    return cookingLikeMapper.delete(userId, cookingId);
  }

  public int likeNumIncr(long cookingId) {
    return cookingMapper.incrLikeNum(cookingId);
  }

  public int likeNumDecr(long cookingId) {
    return cookingMapper.decrLikeNum(cookingId);
  }

}
