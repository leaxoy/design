package com.example.design.controller.restapi;

import com.example.design.annotation.Authorization;
import com.example.design.annotation.CurrentUser;
import com.example.design.constant.Role;
import com.example.design.model.Comment;
import com.example.design.model.User;
import com.example.design.service.impl.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * comment rest api.
 *
 * @author lxh
 * @version 0.1
 */
@RestController
@RequestMapping("api/comment")
@CrossOrigin("*")
public class CommentApi {
  @Autowired
  private CommentService commentService;

  /**
   * @return findAll Comments.
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
  @Authorization({Role.ADMIN, Role.USER})
  public ResponseEntity all() {
    List<Comment> comments = commentService.all();
    if (comments == null) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(comments, HttpStatus.OK);
  }

  /**
   * @param id comment findById.
   * @return Comment.
   */
  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  public ResponseEntity id(@PathVariable int id) {
    Comment comment = commentService.findById(id);
    if (comment == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(comment);
  }

  /**
   * @param id comment findById.
   * @return comment content.
   */
  @RequestMapping("{id}/comment")
  public ResponseEntity comment(@PathVariable int id) {
    Comment comment = commentService.findById(id);
    if (comment == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(comment.getCommentContent());
  }

  /**
   * @param id comment findById.
   * @return comment date.
   */
  @RequestMapping("{id}/commentdate")
  public ResponseEntity commentDate(@PathVariable int id) {
    Comment comment = commentService.findById(id);
    if (comment == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(comment.getCommentDate());
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  @Authorization
  public ResponseEntity<Comment> add(@RequestBody Comment comment, @CurrentUser User user) {
    comment.setUserId(user.getUserId());
    int count = commentService.add(comment);
    if (count == 0) {
      return ResponseEntity.ok(null);
    }
    return ResponseEntity.ok(comment);
  }


  @RequestMapping(value = "{id}", method = RequestMethod.PUT)
  @Authorization
  public ResponseEntity<Comment> update(@PathVariable long id,
                                        @RequestBody Comment comment,
                                        @CurrentUser User user) {
    comment.setUserId(user.getUserId());
    comment.setCommentId(id);
    int count = commentService.update(comment);
    if (count == 0) {
      return ResponseEntity.ok(null);
    }
    return ResponseEntity.ok(comment);
  }
}
