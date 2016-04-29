package com.example.design.service.impl;

import com.example.design.mapper.ShowLikeMapper;
import com.example.design.mapper.ShowMapper;
import com.example.design.model.Show;
import com.example.design.model.ShowLike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户作品服务 Created by lxh on 4/14/16.
 */
@Service
public class ShowService {
  @Autowired
  ShowMapper showMapper;
  @Autowired
  ShowLikeMapper showLikeMapper;

  /**
   * add one cookingShow to a table.
   */
  public int addShow(Show show) {
    return showMapper.addShow(show);
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
  public int updateShow(Show show) {
    return showMapper.updateShow(show);
  }

  /**
   * mark show'state as "deleted".
   */
  public int markShowDelete(long showId) {
    return showMapper.markShowDelete(showId);
  }

  /**
   * select one show by it's Id.
   *
   * @return Show
   */
  public Show findShowById(long showId) {
    return showMapper.findShowById(showId);
  }

  /**
   * select one user's all show.
   *
   * @return List
   */
  public List<Show> findAllShowByUserId(long userId) {
    return showMapper.findAllShowByUserId(userId);
  }

  /**
   * select one cooking's all show.
   *
   * @return List
   */
  public List<Show> findAllShowByCookingId(long cookingId) {
    return showMapper.findAllShowByCookingId(cookingId);
  }

  public List<Show> all() {
    return showMapper.all();
  }

  /**
   * *if one user click Like,his(her) behavior will be recorded,and set state as 1.
   */
  public int addShowLikeUser(ShowLike showLike) {
    return showLikeMapper.addShowLikeUser(showLike);
  }

  /**
   * update showLike's state. delete showLike's record
   * delete one showLike record
   * @param userId
   * @param showId
   * @return
   */
  public int deleteShowLike(long userId, long showId) {
    return showLikeMapper.deleteShowLike(userId, showId);
  }

  public int likeNumIncr(long showId) {
    return showMapper.likeNumIncr(showId);
  }

  public int likeNumDecr(long showId) {
    return showMapper.likeNumDecr(showId);
  }
}
