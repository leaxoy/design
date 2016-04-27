package com.example.design.service.impl;

import com.example.design.mapper.CookingMenuMapper;
import com.example.design.mapper.MenuLikeMapper;
import com.example.design.mapper.MenuMapper;
import com.example.design.model.Menu;
import com.example.design.model.MenuCooking;
import com.example.design.model.MenuLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单的服务接口
 *
 * @author lxh
 * @version 0.1
 */
@Service
public class MenuService {
  @Autowired
  MenuMapper menuMapper;
  MenuLikeMapper menuLikeMapper;
  CookingMenuMapper cookingMenuMapper;

  /**
   * add a menu
   *
   * @param menu
   * @return
   */
  public int addMenu(Menu menu) {
    return menuMapper.addMenu(menu);
  }

  /**
   * select all information by menuId
   *
   * @param menuId
   * @return Menu
   */
  public Menu findByID(long menuId) {
    return menuMapper.findById(menuId);
  }

  /**
   * update menu's information except authorID,menuId,menuLike menuDate,state
   *
   * @param menu
   * @return
   */
  public int updateMenu(Menu menu) {
    return menuMapper.updateMenu(menu);
  }

  /**
   * mark user's one menu as deleted
   *
   * @param menuId
   * @return
   */
  public int markMenuDelete(long menuId) {
    return menuMapper.markMenuDelete(menuId);
  }

  /**
   * select one user's all menu by user's Id
   *
   * @param userID
   * @return
   */
  public List<Menu> findAllMenuByUserID(long userID) {
    return menuMapper.findAllMenuByUserID(userID);
  }

  /**
   * select all menu
   *
   * @return
   */
  public List<Menu> all() {
    return menuMapper.all();
  }

  /**
   * if one user click Like,his(her) behavior will be recorded,and set state as 1
   *
   * @param menuLike
   * @return
   */
  public int addMenuLikeUser(MenuLike menuLike) {
    return menuLikeMapper.addMenuLikeUser(menuLike);
  }

  /**
   * select user's one menu-like record
   *
   * @param userId
   * @param menuId
   * @return MenuLike
   */
  public MenuLike isLike(long userId, long menuId) {
    return menuLikeMapper.isLike(userId, menuId);
  }

  /**
   * update menuLike's state
   *
   * @param like
   * @param userId
   * @param menuId
   * @return
   */
  public int markMenuLikeState(int like, long userId, long menuId) {
    return menuLikeMapper.markMenuLikeState(like, userId, menuId);
  }

  /**
   * update menu's menuLike
   *
   * @param menuId
   * @param like
   * @return
   */
  public int updateLikeOfMenu(long menuId, int like) {
    return menuLikeMapper.updateLikeOfMenu(menuId, like);
  }

  /**
   * add one recipe to a menu
   *
   * @param menuCooking
   * @return
   */
  public int addCookingToMenu(MenuCooking menuCooking) {
    return cookingMenuMapper.addCookingToMenu(menuCooking);
  }

  /**
   * delete one recipe from a menu
   *
   * @param menuId
   * @param cookingId
   * @return
   */
  public int deleteCookingFromMenu(long menuId, long cookingId){
    return cookingMenuMapper.deleteCookingFromMenu(menuId, cookingId);
  }
}
