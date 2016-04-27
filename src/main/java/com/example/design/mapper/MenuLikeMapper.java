package com.example.design.mapper;

import com.example.design.model.MenuLike;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by lxh on 16/4/25.
 */
@Repository
public interface MenuLikeMapper {
  /**
   * if one user click Like,his(her) behavior will be recorded,and set state as 1
   */
  @Insert("INSERT INTO menulike(menuId, userId) VALUES (#{menuId}, #{userId})")
  int addMenuLikeUser(MenuLike menuLike);

  /**
   * select user's one menu-like record
   *
   * @return MenuLike
   */
  @Select("SELECT * FROM menulike WHERE userId = *{userId} AND menuId = #{menuId}")
  MenuLike isLike(long userId, long menuId);

  /**
   * update menuLike's state
   */
  @Update("UPDATE menulike SET state = #{like} WHERE userId = #{userId} AND menuId = #{menuId}")
  int markMenuLikeState(int like, long userId, long menuId);

  /**
   * update menu's menuLike
   */
  @Update("UPDATE menu SET menuLikeNum = menuLikeNum + #{like} WHERE menuId = #{menuId}")
  int updateLikeOfMenu(long menuId, int like);
}
