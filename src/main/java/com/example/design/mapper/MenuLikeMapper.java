package com.example.design.mapper;

import com.example.design.model.MenuLike;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * menu_like mapper.
 *
 * @author lxh
 * @version 0.1
 */
@Repository
@Mapper
public interface MenuLikeMapper {
  /**
   * if one user click Like,his(her) behavior will be recorded,and set state as 1.
   */
  @Insert("INSERT INTO `menu_like`(`menuId`, `userId`) VALUES (#{menuId}, #{userId})")
  int addMenuLikeUser(MenuLike menuLike);

  /**
   * select user's one menu-like record.
   *
   * @return MenuLike
   */
  @Select("SELECT * FROM menulike WHERE userId = *{userId} AND menuId = #{menuId}")
  MenuLike isLike(long userId, long menuId);

  /**
   * delete menuLike's record.
   */
  @Delete("DELETE FROM `menu_like` WHERE `userId` = #{userId} AND `menuId` = #{menuId}")
  int deleteMenuLike(@Param("userId") long userId, @Param("menuId") long menuId);

  /**
   * update menu's menuLikeNum.
   */
  @Update("UPDATE menu SET menuLikeNum = menuLikeNum + #{like} WHERE menuId = #{menuId}")
  int updateLikeOfMenu(long menuId, int like);
}
