package com.example.design.mapper;

import com.example.design.component.model.Page;
import com.example.design.model.Cooking;
import com.example.design.model.MenuCooking;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * cooking-menu mapper.
 *
 * @author lxh
 * @version 0.1
 */
@Repository
@Mapper
public interface CookingMenuMapper {

  @Select("SELECT FROM `menu_cooking` WHERE `menuId` = #{menuId}")
  List<MenuCooking> findByMenuId(@Param("menuId") long menuId);

  @Select("SELECT FROM `menu_cooking` WHERE `menuId` = #{menuId} LIMIT #{offset}, #{limit}")
  List<MenuCooking> findByMenuIdAndPage(@Param("menuId") long menuId, Page page);

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
   * select one menu's findAll recipes.
   */
  @Select("SELECT * FROM `cooking` WHERE `cookingId` IN (SELECT `cookingId` FROM `menu_cooking` "
          + "WHERE `menuId` = #{menuId} AND `state`=0) AND `state`=0")
  List<Cooking> findAllCookingOfMenu(@Param("menuId") long menuId);
}
