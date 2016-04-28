package com.example.design.mapper;

import com.example.design.model.MenuCooking;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
<<<<<<< HEAD
import org.apache.ibatis.annotations.Select;

import java.util.List;
=======
import org.springframework.stereotype.Repository;
>>>>>>> 99c8e9225fc2f84a4ddb13569c6242ed62829de2

/**
 * Created by viver on 16/4/27.
 */
@Repository
public interface CookingMenuMapper {
  /**
   * add one recipe to a menu.
   */
  @Insert("INSERT INTO menu_cooking(menuId, cookingId) VALUES(#{menuId}, #{cookingId}) ")
  int addCookingToMenu(MenuCooking menuCooking);

  /**
   * delete one recipe from a menu.
   */
  @Delete("DELETE  FROM menu_cooking WHERE menuId = #{menuId} AND cookingId = #{cookingId} ")
  int deleteCookingFromMenu(MenuCooking menuCooking);

  /**
   * select one menu's  all recipes
   */
  @Select("SELECT * FROM menu_cooking WHERE menuId = #{menuId}")
  List<MenuCooking> findAllCookingOfMenu(long menuId);
}
