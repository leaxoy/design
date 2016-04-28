package com.example.design.controller.restapi;

import com.example.design.authorization.annotation.Authorization;
import com.example.design.authorization.annotation.CurrentUser;
import com.example.design.constant.Role;
import com.example.design.model.Comment;
import com.example.design.model.User;
import com.example.design.service.impl.CommentService;
import com.example.design.service.impl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
   * 修改用户个人信息.
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
  @Authorization({Role.USER})
  public ResponseEntity changeInfo(@RequestBody User user) {
    int count = userService.updateInfo(user);
    if (1 == count) {
      return ResponseEntity.ok(user);
    }
    return ResponseEntity.ok("修改失败");

  }

  /**
   * 修改密码.
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
  @Authorization({Role.USER})
  public ResponseEntity changePassword(@CurrentUser User user, String password, String newPasswd) {
    String account = user.getAccount();
    User old = userService.getByAccountName(account);
    Assert.notNull(password, "password cannot be empty");
    Assert.notNull(newPasswd, "new password cannot be empty");
    if (password.equals(old.getPassword())) {
      userService.updatePassword(account, newPasswd);
    }
    return ResponseEntity.ok("密码有误");
  }

}
