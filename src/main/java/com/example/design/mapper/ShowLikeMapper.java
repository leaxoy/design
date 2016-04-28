package com.example.design.mapper;

import com.example.design.model.ShowLike;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by lxh on 16/4/25.
 */
@Repository
@Mapper
public interface ShowLikeMapper {
  /**
   * *if one user click Like,his(her) behavior will be recorded,and set state as 1
   */
  @Insert("INSERT INTO showlike(showId, userId) VALUES (#{showId}, #{userId})")
  int addShowLikeUser(ShowLike showLike);

  /**
   * select user's one show-like record
   *
   * @return ShowLike
   */
  @Select("SELECT * FROM showlike WHERE userId = *{userId} AND showId = #{showId}")
  ShowLike isLike(long userId, long showId);

  /**
   * delete showLike's record
   */
  @Delete("DELETE FROM showlike WHERE userId = #{userId} AND showId = #{showId}")
  int deleteShowLike(long userId, long showId);

  /**
   * update show's showLike
   */
  @Update("UPDATE show SET showLikeNum = showLikeNum + #{like} WHERE showId = #{showId}")
  int updateLikeOfShow(long showId, int like);
}
