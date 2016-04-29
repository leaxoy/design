package com.example.design.controller.restapi;

import com.example.design.authorization.annotation.Authorization;
import com.example.design.constant.Role;
import com.example.design.model.Comment;
import com.example.design.model.Cooking;
import com.example.design.model.Menu;
import com.example.design.model.Show;
import com.example.design.model.User;
import com.example.design.service.impl.CommentService;
import com.example.design.service.impl.CookingService;
import com.example.design.service.impl.MenuService;
import com.example.design.service.impl.ShowService;
import com.example.design.service.impl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * user rest api.
 *
 * @author lxh
 * @version 0.1
 */
@RestController
@RequestMapping("api/user")
public class UserApi {
  @Autowired
  private UserService userService;
  @Autowired
  private CommentService commentService;
  @Autowired
  private CookingService cookingService;
  @Autowired
  private ShowService showService;
  @Autowired
  private MenuService menuService;


  /**
   * 获取用户信息
   *
   * @param id user id.
   * @return user.
   */
  @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
  @ApiParam(name = "id", value = "用户ID", required = true)
  @RequestMapping("{id}")
  public ResponseEntity getById(@PathVariable long id) {
    User user = userService.id(id);
    if (user == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(user);
  }

  /**
   * 获取用户作品.
   *
   * @param id 用户id.
   * @return 作品列表.
   */
  @RequestMapping("{id}/article")
  @Authorization({Role.ADMIN, Role.USER})
  public ResponseEntity getArticlesByUserId(@PathVariable long id) {
    return null;
  }

  /**
   * 获取用户评论
   *
   * @param id user id.
   * @return user's comments
   */
  @RequestMapping("{id}/comment")
  @Authorization({Role.USER, Role.ADMIN})
  public ResponseEntity getCommentsByUserId(@PathVariable long id) {
    List<Comment> comments = commentService.byUserId(id);
    if (comments == null) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(comments, HttpStatus.OK);
  }

  /**
   * 获取用户菜谱.
   *
   * @param id 用户id.
   * @return 菜谱列表.
   */
  @RequestMapping("{id}/cooking")
  public ResponseEntity getCookingsByUserId(@PathVariable long id) {
    return null;
  }

  /**
   * 获取用户点赞信息.
   *
   * @param id 用户id.
   * @return 点赞数据.
   */
  @RequestMapping("{id}/like")
  public ResponseEntity getLikesInfoByUserId(@PathVariable long id) {
    return null;
  }

  /**
   * 获取用户朋友.
   *
   * @param id 用户id.
   * @return 朋友列表.
   */
  @RequestMapping("{id}/friend")
  public RequestMapping getFriendsByUserId(@PathVariable long id) {
    return null;
  }

  /**
   * 获取菜单信息.
   *
   * @param id 用户id.
   * @return 菜单数据.
   */
  @RequestMapping("{id}/menu")
  public ResponseEntity getMenusByUserId(@PathVariable long id) {
    return null;
  }

  /**
   * 获取关注信息
   *
   * @param id 用户id.
   * @return 关注信息.
   */
  @RequestMapping("{id}/star")
  public ResponseEntity getStarsByUserId(@PathVariable long id) {
    return null;
  }

  @RequestMapping("{id}/follow")
  public ResponseEntity getFollowsByUserId(@PathVariable long id) {
    return null;
  }

  @RequestMapping("{id}/report")
  public ResponseEntity getReportsByUserId(@PathVariable long id) {
    return null;
  }

  @RequestMapping("{id}/message")
  public ResponseEntity getMessageByUserId(@PathVariable long id) {
    return null;
  }

  /**
   * 返回指定用户id 的菜谱列表.
   *
   * @param id 用户 id.
   * @return 菜谱列表.
   */
  @RequestMapping(value = "{id}/cooking", method = RequestMethod.GET)
//  @Authorization({Role.USER, Role.ADMIN, Role.GUEST, Role.LIMITED_USER})
  public ResponseEntity cookingByuserId(@PathVariable long id) {
    List<Cooking> list = cookingService.findAllCookingByUserId(id);
    if (list == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(list);
  }

  /**
   * 返回指定用户id 的作品列表.
   *
   * @param id 用户 id.
   * @return 作品列表.
   */
  @RequestMapping(value = "{id}/show", method = RequestMethod.GET)
//  @Authorization({Role.USER, Role.ADMIN, Role.LIMITED_USER, Role.GUEST})
  public ResponseEntity showByUserId(@PathVariable long id) {
    List<Show> list = showService.findAllShowByUserId(id);
    if (list == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(list);
  }

  /**
   * 返回指定用户id 的菜单列表.
   *
   * @param id 用户 id.
   * @return 菜单列表.
   */
  @RequestMapping(value = "{id}/menu", method = RequestMethod.GET)
//  @Authorization({Role.USER, Role.ADMIN, Role.LIMITED_USER, Role.GUEST})
  public ResponseEntity menuByUserId(@PathVariable long id) {
    List<Menu> list = menuService.findAllMenuByUserId(id);
    if (list == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(list);
  }
}
