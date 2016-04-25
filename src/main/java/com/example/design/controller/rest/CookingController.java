package com.example.design.controller.rest;

import com.example.design.authorization.annotation.Authorization;
import com.example.design.constant.UserRole;
import com.example.design.model.Cooking;
import com.example.design.service.impl.CookingService;

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
 * cooking rest api.
 *
 * @author lxh
 * @version 0.1
 */
@RestController
@RequestMapping("api/cooking")
public class CookingController {

  @Autowired
  private CookingService cookingService;

  /**
   * 返回所有菜谱.
   *
   * @return all articles list.
   */
  @RequestMapping("")
  @Authorization({UserRole.ADMIN, UserRole.USER, UserRole.GUEST, UserRole.ROOT})
  public ResponseEntity all() {
    List<Cooking> list = cookingService.all();
    if (list != null) {
      return new ResponseEntity<>(list, HttpStatus.OK);
    }
    return ResponseEntity.notFound().build();
  }

  /**
   * 返回指定id的菜谱.
   *
   * @param id 菜谱id.
   * @return 指定id 的菜谱.
   */
  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  public ResponseEntity id(@PathVariable long id) {
    Cooking cooking = cookingService.id(id);

    if (cooking == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(cooking);
  }


  /**
   * 返回指定用户id 的菜谱列表.
   *
   * @param userId 用户 id.
   * @return 菜谱列表.
   */
  @RequestMapping(value = "user/{userId}", method = RequestMethod.GET)
  @Authorization({UserRole.USER, UserRole.ROOT, UserRole.ADMIN})
  public ResponseEntity userId(@PathVariable long userId) {
    List<Cooking> list = cookingService.findByUserId(userId);
    if (list == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(list);
  }

  /**
   * 新添加菜谱.
   *
   * @param cooking 菜谱body.
   * @return 新添加的菜谱信息.
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity add(@RequestBody Cooking cooking) {
    return ResponseEntity.ok(cooking);
  }

  /**
   * 更新已存在菜谱.
   *
   * @param cooking 菜谱body.
   * @return 更改的菜谱信息.
   */
  @RequestMapping(value = "{id}", method = RequestMethod.PUT)
  @Authorization({UserRole.ADMIN, UserRole.ROOT, UserRole.USER})
  public ResponseEntity update(@RequestBody Cooking cooking) {
    return ResponseEntity.ok(cooking);
  }

  /**
   * 删除指定id 的菜谱.
   *
   * @param id 菜谱id
   * @return 删除的菜谱信息.
   */
  @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
  public ResponseEntity remove(@PathVariable long id) {
    return ResponseEntity.ok(null);
  }

}
