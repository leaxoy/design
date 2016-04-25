package com.example.design.controller.rest;

import com.example.design.authorization.annotation.Authorization;
import com.example.design.constant.UserRole;
import com.example.design.model.Article;
import com.example.design.service.impl.ArticleService;

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
 * article rest api. Created by lxh on 16/4/25.
 */
@RestController
@RequestMapping("api/article")
public class ArticleController {

  /**
   * articleService DI.
   */
  @Autowired
  private ArticleService articleService;

  /**
   * 返回所有作品.
   *
   * @return all articles list.
   */
  @RequestMapping("")
  @Authorization({UserRole.ADMIN, UserRole.USER, UserRole.GUEST, UserRole.ROOT})
  public ResponseEntity all() {
    List<Article> list = articleService.all();
    if (list != null) {
      return new ResponseEntity<>(list, HttpStatus.OK);
    }
    return ResponseEntity.notFound().build();
  }

  /**
   * 返回指定id的作品.
   *
   * @param id 作品id.
   * @return 指定id 的作品.
   */
  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  public ResponseEntity id(@PathVariable long id) {
    Article article = articleService.id(id);

    if (article == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(article);
  }


  /**
   * 返回指定用户id 的作品列表.
   *
   * @param userId 用户 id.
   * @return 作品列表.
   */
  @RequestMapping(value = "user/{userId}", method = RequestMethod.GET)
  @Authorization({UserRole.USER, UserRole.ROOT, UserRole.ADMIN})
  public ResponseEntity userId(@PathVariable long userId) {
    List<Article> list = articleService.findByUserId(userId);
    if (list == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(list);
  }

  /**
   * 新添加作品.
   *
   * @param article 作品body.
   * @return 新添加的作品信息.
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity add(@RequestBody Article article) {
    return ResponseEntity.ok(article);
  }

  /**
   * 更新已存在作品.
   *
   * @param article 作品body.
   * @return 更改的作品信息.
   */
  @RequestMapping(value = "{id}", method = RequestMethod.PUT)
  @Authorization({UserRole.ADMIN, UserRole.ROOT, UserRole.USER})
  public ResponseEntity update(@RequestBody Article article) {
    return ResponseEntity.ok(article);
  }

  /**
   * 删除指定id 的作品.
   *
   * @param id 作品id
   * @return 删除的作品信息.
   */
  @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
  public ResponseEntity remove(@PathVariable long id) {
    return ResponseEntity.ok(null);
  }
}
