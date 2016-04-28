package com.example.design.controller.rest;

import com.example.design.authorization.annotation.Authorization;
import com.example.design.constant.Role;
import com.example.design.model.Cooking;
import com.example.design.model.CookingLike;
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
  @RequestMapping(value = "", method = RequestMethod.GET)
  @Authorization({Role.ADMIN, Role.USER, Role.GUEST, Role.ROOT})
  public ResponseEntity all() {
    List<Cooking> list = cookingService.all();
    if (list != null) {
      return new ResponseEntity<>(list, HttpStatus.OK);
    }
    return ResponseEntity.notFound().build();
  }

//  /**
//   * 返回所有菜谱.
//   *
//   * @return all articles list by keywords.
//   */
//  @RequestMapping(value ="" , method = RequestMethod.GET)
//  @Authorization({Role.ADMIN, Role.USER, Role.GUEST, Role.ROOT, Role.LIMITED_USER})
//  public ResponseEntity findALLByKeyWords(@PathVariable String keywords) {
//    List<Cooking> list = cookingService.findAllCookingByKeywords(keywords);
//    if (list != null) {
//      return new ResponseEntity<>(list, HttpStatus.OK);
//    }
//    return ResponseEntity.notFound().build();
//  }

  /**
   * 返回指定id的菜谱.
   *
   * @param cookingId 菜谱id.
   * @return 指定id 的菜谱.
   */
  @RequestMapping(value = "{cookingId}", method = RequestMethod.GET)
//  @Authorization({Role.ADMIN, Role.USER, Role.GUEST, Role.LIMITED_USER})
  public ResponseEntity cookingId(@PathVariable long cookingId) {
    Cooking cooking = cookingService.findById(cookingId);

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
  @Authorization({Role.USER, Role.ROOT, Role.ADMIN})
  public ResponseEntity userId(@PathVariable long userId) {
    List<Cooking> list = cookingService.findAllCookingByUserId(userId);
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
  @Authorization({Role.USER})
  public ResponseEntity add(@RequestBody Cooking cooking) {
    /**
     * 创建菜单
     */
    int count = cookingService.addCooking(cooking);

    if (count == 1) {
      return ResponseEntity.ok(cooking);
    }
    return ResponseEntity.ok("添加失败");
  }

  /**
   * 更新已存在菜谱.
   *
   * @param cooking 菜谱body.
   * @return 更改的菜谱信息.
   */
  @RequestMapping(value = "{id}", method = RequestMethod.PUT)
  @Authorization({Role.USER})
  public ResponseEntity update(@RequestBody Cooking cooking) {
    int count = cookingService.updateCooking(cooking);

    if (count == 1) {
      return ResponseEntity.ok(cooking);
    }
    return ResponseEntity.ok("更新失败");
  }

  /**
   * 删除指定id 的菜谱.
   *
   * @param id 菜谱id
   * @return 删除的菜谱信息.
   */
  @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
  @Authorization({Role.ADMIN, Role.USER})
  public ResponseEntity markDelete(@PathVariable long id) {
    int count = cookingService.markCookingDetele(id);
    if (count == 1) {
      return ResponseEntity.ok("删除成功");
    }
    return ResponseEntity.ok("删除失败");
  }

  /**
   * 对某一菜谱点赞或取消赞
   */
  @RequestMapping(value = "like", method = RequestMethod.POST)
  @Authorization({Role.ADMIN, Role.USER})
  public ResponseEntity LikeIt(@RequestBody CookingLikeForm cookingLikeForm) {
    CookingLike cookingLike = new CookingLike();
    cookingLike.setCookingId(cookingLikeForm.getCookingId());
    cookingLike.setUserId(cookingLikeForm.getUserId());
    /**
     * 根据like的值判断是点赞还是取消点赞
     */
    if (cookingLikeForm.getLike() > 0) {
      cookingService.addCookingLikeUser(cookingLike);
      cookingService.updateLikeOfCooking(cookingLike.getCookingId(), 1);
      return ResponseEntity.ok("已点赞");
    }
    cookingService.deleteCookingLike(cookingLike.getUserId(), cookingLike.getCookingId());
    cookingService.updateLikeOfCooking(cookingLike.getCookingId(), -1);
    return ResponseEntity.ok("已取消点赞");
  }


  public static class CookingLikeForm {
    private long cookingId;
    private long userId;
    private int like;

    public CookingLikeForm(long cookingId, long userId, int like) {
      this.cookingId = cookingId;
      this.userId = userId;
      this.like = like;
    }

    public long getCookingId() {
      return cookingId;
    }

    public long getUserId() {
      return userId;
    }

    public int getLike() {
      return like;
    }


  }


}

