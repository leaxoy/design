package com.example.design.controller.restapi;

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
public class MenuApi {

  @Autowired
  private MenuService menuService;

  /**
   * 返回所有作品.
   *
   * @return all articles list.
   */
  @RequestMapping("")
  @Authorization({Role.ADMIN, Role.USER, Role.GUEST, Role.LIMITED_USER})
  public ResponseEntity all() {
    List<Menu> list = menuService.all();
    if (list != null) {
      return new ResponseEntity<>(list, HttpStatus.OK);
    }
    return ResponseEntity.notFound().build();
  }

  /**
   * 返回指定id的作品.
   *
   * @param menuId 作品id.
   * @return 指定id 的作品.
   */
  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  @Authorization({Role.ADMIN, Role.USER, Role.GUEST, Role.LIMITED_USER})
  public ResponseEntity showId(@PathVariable long menuId) {
    Menu menu = menuService.findById(menuId);

    if (menu == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(menu);
  }


  /**
   * 新添加菜单.
   *
   * @param menu 菜单body.
   * @return 新添加的菜单信息.
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
  @Authorization({Role.USER})
  public ResponseEntity add(@RequestBody Menu menu) {
    int count = menuService.addMenu(menu);
    if (count > 0) {
      return ResponseEntity.ok(menu);
    }
    return ResponseEntity.ok("添加失败");
  }

  /**
   * 更新已存在菜单.
   *
   * @param menu 菜单body.
   * @return 更改的作品信息.
   */
  @RequestMapping(value = "{menuId}", method = RequestMethod.PUT)
  @Authorization({Role.USER})
  public ResponseEntity update(@RequestBody Menu menu) {
    int count = menuService.updateMenu(menu);
    if (count > 0) {
      return ResponseEntity.ok(menu);
    }
    return ResponseEntity.ok("更新失败");
  }

  /**
   * 删除指定id 的菜单.
   *
   * @param menuId 菜单id
   * @return 删除菜单.
   */
  @RequestMapping(value = "{menuId}", method = RequestMethod.DELETE)
  @Authorization({Role.ADMIN, Role.USER})
  public ResponseEntity markDelete(@PathVariable long menuId) {
    int count = menuService.markMenuDelete(menuId);
    if (count > 0) {
      return ResponseEntity.ok("删除成功");
    }
    return ResponseEntity.ok("删除失败");
  }


  /**
   * 为某一菜单添加菜谱
   */
  @RequestMapping(value = "{menuId}/cooking/{cookingId}", method = RequestMethod.POST)
  @Authorization({Role.USER})
  public ResponseEntity addShowToCooking(MenuCooking menuCooking) {
    int count = menuService.addCookingToMenu(menuCooking);
    if (count > 0) {
      return ResponseEntity.ok("添加成功");
    }
    return ResponseEntity.ok("添加失败");
  }

  /**
   * 将菜谱从菜单中删除
   */
  @RequestMapping(value = "{menuId}/cooking/{cookingId}", method = RequestMethod.DELETE)
  @Authorization({Role.ADMIN, Role.USER})
  public ResponseEntity deleteCookingFromMenu(MenuCooking menuCooking) {
    int count = menuService.deleteCookingFromMenu(menuCooking);
    if (count > 0) {
      return ResponseEntity.ok("删除成功");
    }
    return ResponseEntity.ok("删除失败");
  }

  /**
   * 对某一作品点赞或取消赞
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
