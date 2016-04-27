package com.example.design.mapper;

import com.example.design.model.Menu;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单持久化接口. Created by lxh on 4/17/16.
 */
@Repository
public interface MenuMapper {
  /**
   * add a menu
   */
  @Insert("INSERT INTO menu(menuName, menuPicture, authorId, menuDate) VALUES(#{menuName}," +
          " #{menuPicture}, #{authorId}, #{menuDate})")
  int addMenu(Menu menu);

  /**
   * select all information by menuId
   *
   * @return Menu
   */
  @Select("SELECT * FROM menu WHERE menuId = #{menuId}")
  Menu findById(long menuId);

  /**
   * update menu's information except authorId,menuId,menuLike menuDate,state
   */
  @Update("UPDATE menu SET menuName = #{menuName}, menuPicture = #{menuPicture} WHERE authorId =" +
          " #{authorId} AND menuId = #{menuId}" +
          " AND state = 0")
  int updateMenu(Menu menu);

  /**
   * select one user's all menu by author's Id
   */
  @Select("SELECT * FROM menu WHERE authorId = #{authorId} AND state = 0")
  List<Menu> findAllMenuByUserID(@Param("authorId") long userId);

  /**
   * mark user's one menu as deleted
   */
  @Update("UPDATE menu SET state = 1 WHERE menuID = #{menuID} ")
  int markMenuDelete(long menuID);

  /**
   * select all menus
   */
  @Select("SELECT * FROM menu")
  List<Menu> all();
}
