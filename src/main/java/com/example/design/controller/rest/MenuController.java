package com.example.design.controller.rest;

import com.example.design.authorization.annotation.Authorization;
import com.example.design.constant.Role;
import com.example.design.model.Menu;
import com.example.design.model.MenuCooking;
import com.example.design.model.MenuLike;
import com.example.design.service.impl.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * menu rest api.
 *
 * @author lxh
 * @version 0.1
 */
@RestController
@RequestMapping("api/menu")
public class MenuController {
  @Autowired
  private MenuService menuService;

  /**
   * 返回所有菜单
   *
   * @return all menu list
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
  @Authorization({Role.ADMIN, Role.ROOT, Role.GUEST, Role.USER})
  public ResponseEntity all() {
    List<Menu> list = menuService.all();
    if (list != null) {
      return new ResponseEntity<>(list, HttpStatus.OK);
    }
    return ResponseEntity.notFound().build();
  }

  /**
   * 返回指定用户id的菜单列表
   *
   * @return 菜单列表
   */
  @RequestMapping(value = "user/{userId}", method = RequestMethod.GET)
  @Authorization({Role.USER, Role.ROOT, Role.ADMIN})
  public ResponseEntity userId(@PathVariable long userId) {
    List<Menu> list = menuService.findAllMenuByUserId(userId);
    if (list == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(list);
  }

  /**
   * 添加菜单,不包含菜谱
   *
   * @return 新添加的菜谱信息
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
  @Authorization({Role.USER})
  public ResponseEntity add(@RequestBody Menu menu) {
    /**
     * 创建菜单
     */
    int count = menuService.addMenu(menu);
    if (count == 1) {
      return ResponseEntity.ok(menu);
    }
    return ResponseEntity.ok("添加失败");
  }

  /**
   * 更新已存在的指定id的菜单基础信息
   *
   * @return 更改菜单的信息
   */
  @RequestMapping(value = "", method = RequestMethod.PUT)
  @Authorization({Role.USER})
  public ResponseEntity update(@RequestBody Menu menu) {
    /**
     * 创建菜单
     */
    int count = menuService.updateMenu(menu);
    if (count == 1) {
      return ResponseEntity.ok(menu);
    }
    return ResponseEntity.ok("更新失败");
  }

  /**
   * 删除菜单
   */
  @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
  @Authorization({Role.ADMIN, Role.USER})
  public ResponseEntity markDelete(@PathVariable long id) {
    int count = menuService.markMenuDelete(id);
    if (count == 1) {
      return ResponseEntity.ok("删除成功");
    }
    return ResponseEntity.ok("删除失败");
  }

  /**
   * 添加菜谱到菜单中
   *
   * @return 添加的菜谱菜单
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
  @Authorization({Role.USER})
  public ResponseEntity addCookingToMenu(MenuCooking menuCooking) {
    int count = menuService.addCookingToMenu(menuCooking);
    if (count == 1) {
      return ResponseEntity.ok(menuCooking);
    }
    return ResponseEntity.ok("添加菜单成功");
  }

  /**
   * 删除某一菜单中的菜谱
   *
   * @return 要删除的菜谱菜单信息
   */
  @RequestMapping(value = "", method = RequestMethod.DELETE)
  @Authorization({Role.USER, Role.ADMIN})
  public ResponseEntity deleteCookingFromMenu(MenuCooking menuCooking) {
    int count = menuService.deleteCookingFromMenu(menuCooking);
    if (count == 1) {
      return ResponseEntity.ok(menuCooking);
    }
    return ResponseEntity.ok("删除成功");
  }

  /**
   * 返回某一菜单的所有菜谱
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
  @Authorization({Role.USER, Role.ADMIN, Role.GUEST, Role.LIMITED_USER})
  public ResponseEntity findAllCookingOfMenu(long menuId) {
    List<MenuCooking> list = menuService.findAllCookingOfMenu(menuId);
    if (list != null) {
      return new ResponseEntity<>(list, HttpStatus.OK);
    }
    return ResponseEntity.notFound().build();
  }

  /**
   * 对某一菜单点赞或取消赞
   */
  @RequestMapping(value = "like", method = RequestMethod.POST)
  @Authorization({Role.USER})
  public ResponseEntity LikeIt(@RequestBody MenuLikeForm menuLikeForm) {
    MenuLike menuLike = new MenuLike();
    menuLike.setMenuId(menuLikeForm.getMenuId());
    menuLike.setUserId(menuLikeForm.getUserId());
    /**
     * 根据like的值判断是点赞还是取消点赞
     */
    if (menuLikeForm.getLike() > 0) {
      menuService.addMenuLikeUser(menuLike);
      menuService.updateLikeOfMenu(menuLike.getMenuId(), 1);
      return ResponseEntity.ok("点赞成功");
    }
    menuService.deleteMenuLike(menuLike.getUserId(), menuLike.getMenuId());
    menuService.updateLikeOfMenu(menuLike.getMenuId(), -1);
    return ResponseEntity.ok("取消点赞");
  }


  public static class MenuLikeForm {
    private long menuId;
    private long userId;
    private int like;

    public MenuLikeForm(long menuId, long userId, int like) {
      this.menuId = menuId;
      this.userId = userId;
      this.like = like;
    }

    public long getMenuId() {
      return menuId;
    }

    public long getUserId() {
      return userId;
    }

    public int getLike() {
      return like;
    }


  }
}
