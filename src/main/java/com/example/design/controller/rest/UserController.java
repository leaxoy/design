package com.example.design.controller.rest;

import com.example.design.authorization.annotation.Authorization;
import com.example.design.constant.UserRole;
import com.example.design.model.Comment;
import com.example.design.model.User;
import com.example.design.service.impl.CommentService;
import com.example.design.service.impl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * user rest api.
 *
 * @author lxh
 * @version 0.1
 */
@RestController
@RequestMapping("api/user")
public class UserController {
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
  @RequestMapping("{id}")
  public ResponseEntity getById(@PathVariable long id) {
    User user = userService.getByAccountName(String.valueOf(id));
    if (user == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(user);
  }

  @RequestMapping("{id}/article")
  @Authorization({UserRole.ADMIN, UserRole.USER})
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
  @Authorization({UserRole.USER, UserRole.ADMIN})
  public ResponseEntity getCommentsByUserId(@PathVariable long id) {
    List<Comment> comments = commentService.findCommentsByUserId(id);
    if (comments == null) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(comments, HttpStatus.OK);
  }

  @RequestMapping("{id}/cooking")
  public ResponseEntity getCookingsByUserId(@PathVariable long id) {
    return null;
  }

  @RequestMapping("{id}/like")
  public ResponseEntity getLikesInfoByUserId(@PathVariable long id) {
    return null;
  }

  @RequestMapping("{id}/friend")
  public RequestMapping getFriendsByUserId(@PathVariable long id) {
    return null;
  }

  @RequestMapping("{id}/menu")
  public ResponseEntity getMenusByUserId(@PathVariable long id) {
    return null;
  }

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

}
