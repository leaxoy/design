package com.example.design.mapper;

import com.example.design.model.CookingLike;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * cooking_like mapper.
 *
 * @author lxh
 * @version 0.1
 */
@Mapper
@Repository
public interface CookingLikeMapper {
  /**
   * if one user click Like,his(her) behavior will be recorded,and set state as 1.
   */
  @Insert("INSERT INTO `cooking_like`(`cookingId`, `userId`) VALUES (#{cookingId}, #{userId})")
  int addCookingLikeUser(CookingLike cookingLike);

  /**
   * update cookingLike's record
   */
  @Delete("DELETE FROM `cooking_like` WHERE `cookingId` = #{cookingId} AND `userId` = #{userId}")
  int deleteCookingLike(@Param("userId") long userId, @Param("cookingId") long cookingId);


}
