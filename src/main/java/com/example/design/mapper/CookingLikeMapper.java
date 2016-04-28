package com.example.design.mapper;

import com.example.design.model.CookingLike;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by lxh on 16/4/25.
 */
@Repository
public interface CookingLikeMapper {
  /**
   * if one user click Like,his(her) behavior will be recorded,and set state as 1
   */
  @Insert("INSERT INTO cookinglike(cookingId, userId) VALUES (#{cookingId}, #{userId})")
  int addCookingLikeUser(CookingLike cookingLike);

  /**
   * select user's one cooking-like record
   */
  @Select("SELECT * FROM cookinglike WHERE userId = *{userId} AND cookingId = #{cookingId}")
  CookingLike isLike(long userId, long cookingId);

  /**
   * update cookingLike's record
   */
  @Delete("DELETE WHERE userId = #{userId} AND cookingId = #{cookingId}")
  int deleteCookingLike(long userId, long cookingId);

  /**
   * update cooking's cooingLikeNum
   */
  @Update("UPDATE cooking SET cookingLikeNum = cookingLikeNum + #{like} WHERE cookingId = #{cookingId}")
  int updateLikeOfCooking(long cookingId, int like);
}
