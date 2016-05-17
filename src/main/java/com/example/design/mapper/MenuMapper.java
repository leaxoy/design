package com.example.design.mapper;

import com.example.design.component.model.Page;
import com.example.design.model.Menu;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单持久化接口.
 *
 * @author lxh
 * @version 0.1
 */
@Repository
@Mapper
public interface MenuMapper {
  /**
   * add a menu.
   */
  @Insert("INSERT INTO `menu`(`menuName`, `menuPicture`, `authorId`, `menuDate`) VALUES"
          + "(#{menuName}, #{menuPicture}, #{authorId}, #{menuDate})")
  int add(Menu menu);


  @Select("SELECT * FROM `menu` LIMIT offset, limit")
  List<Menu> findByPage(Page page);

  /**
   * select findAll information by menuId.
   *
   * @return Menu
   */
  @Select("SELECT * FROM `menu` WHERE `menuId` = #{menuId}")
  Menu findById(@Param("menuId") long menuId);

  /**
   * get top6.
   */
  @Select("SELECT * FROM `menu` ORDER BY `menuLikeNum` DESC LIMIT 6")
  List<Menu> top6();

  /**
   * update menu's information except authorId,menuId,menuLike menuDate,state.
   */
  @Update("UPDATE `menu` SET `menuName` = #{menuName}, `menuPicture` = #{menuPicture} WHERE "
          + "`authorId` = #{authorId} AND `menuId` = #{menuId} AND `state` = 0")
  int updateMenu(Menu menu);

  /**
   * select one user's queryAll menu by author's Id.
   */
  @Select("SELECT * FROM `menu` WHERE `authorId` = #{authorId} AND `state` = 0")
  List<Menu> findByUserId(@Param("authorId") long userId);

  /**
   * mark user's one menu as deleted.
   */
  @Update("UPDATE `menu` SET `state` = 1 WHERE `menuID` = #{menuId} ")
  int markDeleted(@Param("menuId") long menuId);

  /**
   * select findAll menus.
   */
  @Select("SELECT * FROM `menu`")
  List<Menu> all();

  @Update("UPDATE `menu` SET `menuLikeNum` = `menuLikeNum` + 1 WHERE `menuId`"
          + " = #{menuId} AND `state`=0")
  int incrLikeNum(@Param("menuId") long menuId);

  @Update("UPDATE `menu` SET `menuLikeNum` = `menuLikeNum` - 1 WHERE `menuId`"
          + " = #{menuId} AND `state`=0")
  int decrLikeNum(@Param("menuId") long menuId);
}
