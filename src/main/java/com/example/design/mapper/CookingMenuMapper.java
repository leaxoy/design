package com.example.design.mapper;

import com.example.design.model.MenuCooking;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

/**
 * Created by viver on 16/4/27.
 */
public interface CookingMenuMapper {
  /**
   * add one recipe to a menu
   */
  @Insert("INSERT INTO menu_cooking(menuId, cookingId) VALUES(#{menuId}, #{cookingId}) ")
  int addCookingToMenu(MenuCooking menuCooking);

  /**
   * delete one recipe from a menu
   */
  @Delete("DELETE  FROM menu_cooking WHERE menuID = #{menuID} AND cookingID = #{cookingID} ")
  int deleteCookingFromMenu(long menuId, long cookingId);
}
