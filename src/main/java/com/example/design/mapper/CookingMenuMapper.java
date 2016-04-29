package com.example.design.mapper;

import com.example.design.model.Cooking;
import com.example.design.model.MenuCooking;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by viver on 16/4/27.
 */
@Repository
@Mapper
public interface CookingMenuMapper {
  /**
   * add one recipe to a menu.
   */
  @Insert("INSERT INTO `menu_cooking`(`menuId`, `cookingId`) VALUES(#{menuId}, #{cookingId}) ")
  int addCookingToMenu(MenuCooking menuCooking);

  /**
   * delete one recipe from a menu.
   */
  @Delete("DELETE  FROM `menu_cooking` WHERE `menuId` = #{menuId} AND `cookingId` = #{cookingId} ")
  int deleteCookingFromMenu(MenuCooking menuCooking);

  /**
   * select one menu's  all recipes.
   */
  @Select("SELECT * FROM `cooking` WHERE `cookingId` IN (SELECT `cookingId` FROM `menu_cooking` "
          + "WHERE `menuId` = #{menuId} AND `state`=0) AND `state`=0")
  List<Cooking> findAllCookingOfMenu(long menuId);
}
