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
  ShowLikeMapper showLikeMapper;

  /**
   * add one cookingShow to a table
   *
   * @param show
   * @return
   */
  public int addShow(Show show) {
    return showMapper.addShow(show);
  }

  /**
   * Update show information
   *
   * @param show
   * @return
   */
  public int updateShow(Show show) {
    return showMapper.updateShow(show);
  }

  /**
   * mark show'state as "deleted"
   *
   * @param showId
   * @return
   */
  public int markShowDelete(long showId) {
    return showMapper.markShowDelete(showId);
  }

  /**
   * select one show by it's Id
   *
   * @param showId
   * @return Show
   */
  public Show findShowById(long showId) {
    return showMapper.findShowById(showId);
  }

  /**
   * select one user's all show
   *
   * @param userId
   * @return List<Show>
   */
  public List<Show> findAllShowByUserId(long userId) {
    return showMapper.findAllShowByUserId(userId);
  }

  /**
   * select one cooking's all show
   *
   * @param cookingId
   * @return List<Show>
   */
  public List<Show> findAllShowByCookingID(long cookingId) {
    return showMapper.findAllShowByCookingId(cookingId);
  }

  public List<Show> all() {
    return showMapper.all();
  }

  /**
   * *if one user click Like,his(her) behavior will be recorded,and set state as 1
   *
   * @param showLike
   * @return
   */
  public int addShowLikeUser(ShowLike showLike) {
    return showLikeMapper.addShowLikeUser(showLike);
  }

  /**
   * select user's one show-like record
   * @param userId
   * @param showId
   * @return ShowLike
   */
  public ShowLike isLike(long userId, long showId) {
    return showLikeMapper.isLike(userId, showId);
  }

  /**
   * update showLike's state
   *
   * @param like
   * @param userId
   * @param showId
   * @return
   */
  public int markShowLikeState(int like, long userId, long showId) {
    return showLikeMapper.markShowLikeState(like, userId, showId);
  }

  /**
   * update show's showLike
   *
   * @param showId
   * @param like
   * @return
   */
  public int updateLikeOfShow(long showId, int like){
    return showLikeMapper.updateLikeOfShow(showId, like);
  }
}
