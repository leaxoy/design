package com.example.design.mapper;

import com.example.design.model.MenuLike;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by lxh on 16/4/25.
 */
@Repository
public interface MenuLikeMapper {
  /**
   * if one user click Like,his(her) behavior will be recorded,and set state as 1
   */
  @Insert("INSERT INTO `menu_like`(`menuId`, `userId`) VALUES (#{menuId}, #{userId})")
  int addMenuLikeUser(MenuLike menuLike);

  /**
   * delete menuLike's record
   */
  @Delete("DELETE FROM `menu_like` WHERE `userId` = #{userId} AND `menuId` = #{menuId}")
  int deleteMenuLike(@Param("userId") long userId, @Param("menuId") long menuId);

}
