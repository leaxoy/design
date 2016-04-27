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
   */
  public int addMenu(Menu menu) {
    return menuMapper.addMenu(menu);
  }

  /**
   * select all information by menuId
   *
   * @return Menu
   */
  public Menu findByID(long menuId) {
    return menuMapper.findById(menuId);
  }

  /**
   * update menu's information except authorID,menuId,menuLike menuDate,state
   */
  public int updateMenu(Menu menu) {
    return menuMapper.updateMenu(menu);
  }

  /**
   * mark user's one menu as deleted
   */
  public int markMenuDelete(long menuId) {
    return menuMapper.markMenuDelete(menuId);
  }

  /**
   * select one user's all menu by user's Id
   */
  public List<Menu> findAllMenuByUserID(long userID) {
    return menuMapper.findAllMenuByUserID(userID);
  }

  /**
   * select all menu
   */
  public List<Menu> all() {
    return menuMapper.all();
  }

  /**
   * if one user click Like,his(her) behavior will be recorded,and set state as 1
   */
  public int addMenuLikeUser(MenuLike menuLike) {
    return menuLikeMapper.addMenuLikeUser(menuLike);
  }

  /**
   * select user's one menu-like record
   *
   * @return MenuLike
   */
  public MenuLike isLike(long userId, long menuId) {
    return menuLikeMapper.isLike(userId, menuId);
  }

  /**
   * update menuLike's state
   */
  public int markMenuLikeState(int like, long userId, long menuId) {
    return menuLikeMapper.markMenuLikeState(like, userId, menuId);
  }

  /**
   * update menu's menuLike
   */
  public int updateLikeOfMenu(long menuId, int like) {
    return menuLikeMapper.updateLikeOfMenu(menuId, like);
  }

  /**
   * add one recipe to a menu
   */
  public int addCookingToMenu(MenuCooking menuCooking) {
    return cookingMenuMapper.addCookingToMenu(menuCooking);
  }

  /**
   * delete one recipe from a menu
   */
  public int deleteCookingFromMenu(long menuId, long cookingId) {
    return cookingMenuMapper.deleteCookingFromMenu(menuId, cookingId);
  }
}
