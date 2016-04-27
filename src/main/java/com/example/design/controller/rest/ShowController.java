package com.example.design.controller.rest;

import com.example.design.authorization.annotation.Authorization;
import com.example.design.constant.Role;
import com.example.design.model.Show;
import com.example.design.service.impl.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * show rest api.
 *
 * @author lxh
 * @version 0.1
 */
@RestController
@RequestMapping("api/show")
public class ShowController {

  /**
   * articleService DI.
   */
  @Autowired
  private ShowService showService;

  /**
   * 返回所有作品.
   *
   * @return all articles list.
   */
  @RequestMapping("")
  @Authorization({Role.ADMIN, Role.USER, Role.GUEST, Role.ROOT})
  public ResponseEntity all() {
    List<Show> list = showService.all();
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
    Show show = showService.findShowById(id);

    if (show == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(show);
  }


  /**
   * 返回指定用户id 的作品列表.
   *
   * @param userId 用户 id.
   * @return 作品列表.
   */
  @RequestMapping(value = "user/{userId}", method = RequestMethod.GET)
  @Authorization({Role.USER, Role.ROOT, Role.ADMIN})
  public ResponseEntity userId(@PathVariable long userId) {
    List<Show> list = showService.findAllShowByUserId(userId);
    if (list == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(list);
  }

  /**
   * 新添加作品.
   *
   * @param show 作品body.
   * @return 新添加的作品信息.
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity add(@RequestBody Show show) {
    return ResponseEntity.ok(show);
  }

  /**
   * 更新已存在作品.
   *
   * @param show 作品body.
   * @return 更改的作品信息.
   */
  @RequestMapping(value = "{id}", method = RequestMethod.PUT)
  @Authorization({Role.ADMIN, Role.ROOT, Role.USER})
  public ResponseEntity update(@RequestBody Show show) {
    return ResponseEntity.ok(show);
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
