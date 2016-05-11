package com.example.design.service.impl;

import com.example.design.mapper.ShowLikeMapper;
import com.example.design.mapper.ShowMapper;
import com.example.design.model.Show;
import com.example.design.model.ShowLike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户作品服务 Created by lxh on 4/14/16.
 */
@Service
@Transactional
public class ShowService {
  @Autowired
  private ShowMapper showMapper;
  @Autowired
  private ShowLikeMapper showLikeMapper;

  /**
   * add one cookingShow to a table.
   */
  public int add(Show show) {
    return showMapper.add(show);
  }

  /**
   * Update show information. add show to one recipe
   * add show to one recipe
   */
  public int addShowToCooking(long cookingId, long showId) {
    return showMapper.addShowToCooking(cookingId, showId);
  }

  /**
   * Update show information
   */
  public int update(Show show) {
    return showMapper.update(show);
  }

  /**
   * mark show'state as "deleted".
   */
  public int markDeleted(long showId) {
    return showMapper.markDeleted(showId);
  }

  /**
   * select one show by it's Id.
   *
   * @return Show
   */
  public Show findById(long showId) {
    return showMapper.findById(showId);
  }

  /**
   * select one user's queryAll show.
   *
   * @return List
   */
  public List<Show> findByUserId(long userId) {
    return showMapper.findByUserId(userId);
  }

  /**
   * select one cooking's queryAll show.
   *
   * @return List
   */
  public List<Show> findByCookingId(long cookingId) {
    return showMapper.findByCookingId(cookingId);
  }

  public List<Show> findAll() {
    return showMapper.findAll();
  }

  /**
   * *if one user click Like,his(her) behavior will be recorded,and set state as 1.
   */
  public int addShowLikeUser(ShowLike showLike) {
    return showLikeMapper.addShowLikeUser(showLike);
  }

  /**
   * update showLike's state. delete showLike's record delete one showLike record
   */
  public int deleteShowLike(long userId, long showId) {
    return showLikeMapper.deleteShowLike(userId, showId);
  }

  public int likeNumIncr(long showId) {
    return showMapper.incrLikeNum(showId);
  }

  public int likeNumDecr(long showId) {
    return showMapper.decrLikeNum(showId);
  }
}
