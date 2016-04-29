package com.example.design.mapper;

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
  int addMenu(Menu menu);

  /**
   * select all information by menuId.
   *
   * @return Menu
   */
  @Select("SELECT * FROM `menu` WHERE `menuId` = #{menuId}")
  Menu findById(long menuId);

  /**
   * update menu's information except authorId,menuId,menuLike menuDate,state.
   */
  @Update("UPDATE `menu` SET `menuName` = #{menuName}, `menuPicture` = #{menuPicture} WHERE "
          + "`authorId` = #{authorId} AND `menuId` = #{menuId} AND `state` = 0")
  int updateMenu(Menu menu);

  /**
   * select one user's all menu by author's Id.
   */
  @Select("SELECT * FROM `menu` WHERE `authorId` = #{authorId} AND `state` = 0")
  List<Menu> findAllMenuByUserId(@Param("authorId") long userId);

  /**
   * mark user's one menu as deleted.
   */
  @Update("UPDATE `menu` SET `state` = 1 WHERE `menuID` = #{menuId} ")
  int markMenuDelete(long menuId);

  /**
   * select all menus.
   */
  @Select("SELECT * FROM `menu`")
  List<Menu> all();

  @Update("UPDATE `menu` SET `menuLikeNum` = `menuLikeNum` + 1 WHERE `menuId`"
          + " = #{menuId} AND `state`=0")
  int likeNumIncr(long menuId);

  @Update("UPDATE `menu` SET `menuLikeNum` = `menuLikeNum` - 1 WHERE `menuId`"
          + " = #{menuId} AND `state`=0")
  int likeNumDecr(long menuId);
}
