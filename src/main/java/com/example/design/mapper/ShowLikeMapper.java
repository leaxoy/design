package com.example.design.mapper;

import com.example.design.model.ShowLike;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
  @Insert("INSERT INTO `show_like`(`showId`, `userId`) VALUES (#{showId}, #{userId})")
  int addShowLikeUser(ShowLike showLike);


  /**
   * delete showLike's record
   */
  @Delete("DELETE FROM `show_like` WHERE `userId` = #{userId} AND `showId` = #{showId}")
  int deleteShowLike(@Param("userId") long userId, @Param("showId") long showId);

}
