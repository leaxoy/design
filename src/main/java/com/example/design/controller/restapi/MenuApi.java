package com.example.design.controller.restapi;

import com.example.design.model.Cooking;
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
   * 返回所有菜单.
   *
   * @return findAll menu list.
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
//  @Authorization({Role.ADMIN, Role.USER, Role.GUEST, Role.LIMITED_USER})
  public ResponseEntity all() {
    List<Menu> list = menuService.all();
    if (list != null) {
      return new ResponseEntity<>(list, HttpStatus.OK);
    }
    return ResponseEntity.notFound().build();
  }


  @RequestMapping(value = "top6", method = RequestMethod.GET)
  public ResponseEntity top6() {
    List<Menu> menus = menuService.top6();
    if (menus == null) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(menus, HttpStatus.OK);
  }

  /**
   * 返回指定id的菜单.
   *
   * @param menuId 菜单id.
   * @return 指定id 的菜单.
   */
  @RequestMapping(value = "{menuId}", method = RequestMethod.GET)
//  @Authorization({Role.ADMIN, Role.USER, Role.GUEST, Role.LIMITED_USER})
  public ResponseEntity menuId(@PathVariable long menuId) {
    Menu menu = menuService.findById(menuId);

    if (menu == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(menu);
  }

  /**
   * 返回指定id的菜单中的菜谱
   *
   * @param menuId 菜单id.
   * @return 指定id的菜单中的菜谱.
   */
  @RequestMapping(value = "{menuId}/cooking", method = RequestMethod.GET)
//  @Authorization({Role.ADMIN, Role.USER, Role.GUEST, Role.LIMITED_USER})
  public ResponseEntity showId(@PathVariable long menuId) {
    List<Cooking> list = menuService.findAllCookingOfMenu(menuId);

    if (list != null) {
      return new ResponseEntity<>(list, HttpStatus.OK);
    }
    return ResponseEntity.notFound().build();
  }

  /**
   * 新添加菜单.
   *
   * @param menu 菜单body.
   * @return 新添加的菜单信息.
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
//  @Authorization({Role.USER})
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
//  @Authorization({Role.USER})
  public ResponseEntity update(@PathVariable long menuId, @RequestBody Menu menu) {
    menu.setMenuId(menuId);
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
//  @Authorization({Role.ADMIN, Role.USER})
  public ResponseEntity markDelete(@PathVariable long menuId) {
    int count = menuService.markMenuDelete(menuId);
    if (count > 0) {
      return ResponseEntity.ok("删除成功");
    }
    return ResponseEntity.ok("删除失败");
  }


  /**
   * 为某一菜单添加菜谱.
   */
  @RequestMapping(value = "{menuId}/cooking/{cookingId}", method = RequestMethod.POST)
//  @Authorization({Role.USER})
  public ResponseEntity addShowToCooking(@PathVariable long menuId, @PathVariable long cookingId) {
    MenuCooking menuCooking = new MenuCooking();
    menuCooking.setMenuId(menuId);
    menuCooking.setCookingId(cookingId);

    int count = menuService.addCookingToMenu(menuCooking);
    if (count > 0) {
      return ResponseEntity.ok("添加成功");
    }
    return ResponseEntity.ok("添加失败");
  }

  /**
   * 将菜谱从菜单中删除.
   */
  @RequestMapping(value = "{menuId}/cooking/{cookingId}", method = RequestMethod.DELETE)
//  @Authorization({Role.ADMIN, Role.USER})
  public ResponseEntity deleteCookingFromMenu(@PathVariable long menuId, @PathVariable long cookingId) {
    MenuCooking menuCooking = new MenuCooking();
    menuCooking.setMenuId(menuId);
    menuCooking.setCookingId(cookingId);
    int count = menuService.deleteCookingFromMenu(menuCooking);
    if (count > 0) {
      return ResponseEntity.ok("删除成功");
    }
    return ResponseEntity.ok("删除失败");
  }

  /**
   * 对某一作品点赞或取消赞.
   */
  @RequestMapping(value = "like", method = RequestMethod.POST)
//  @Authorization({Role.USER})
  public ResponseEntity LikeIt(@RequestBody MenuLikeForm menuLikeForm) {
    MenuLike menuLike = new MenuLike();
    menuLike.setMenuId(menuLikeForm.getMenuId());
    menuLike.setUserId(menuLikeForm.getUserId());
    /**
     * 根据like的值判断是点赞还是取消点赞
     */
    if (menuLikeForm.getLike() > 0) {
      menuService.addMenuLikeUser(menuLike);
      menuService.likeNumIncr(menuLike.getMenuId());
      return ResponseEntity.ok("点赞成功");
    }
    menuService.deleteMenuLike(menuLike.getUserId(), menuLike.getMenuId());
    menuService.likeNumDecr(menuLike.getMenuId());
    return ResponseEntity.ok("取消点赞");
  }


  public static class MenuLikeForm {
    private long menuId;
    private long userId;
    private int like;


    public MenuLikeForm() {
    }

    public long getMenuId() {
      return menuId;
    }

    public void setMenuId(long menuId) {
      this.menuId = menuId;
    }

    public long getUserId() {
      return userId;
    }

    public void setUserId(long userId) {
      this.userId = userId;
    }

    public int getLike() {
      return like;
    }

    public void setLike(int like) {
      this.like = like;
    }
  }
}
