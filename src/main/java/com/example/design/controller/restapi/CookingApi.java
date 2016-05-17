package com.example.design.controller.restapi;

import com.example.design.annotation.Authorization;
import com.example.design.constant.Role;
import com.example.design.model.Cooking;
import com.example.design.model.CookingLike;
import com.example.design.model.Show;
import com.example.design.service.impl.CookingService;
import com.example.design.service.impl.ShowService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

/**
 * cooking rest api.
 *
 * @author lxh
 * @version 0.1
 */
@RestController
@RequestMapping("api/cooking")
@CrossOrigin("*")
public class CookingApi {

  @Autowired
  private CookingService cookingService;
  @Autowired
  private ShowService showService;

  /**
   * 返回所有菜谱.
   *
   * @return findAll cooking list.
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity all() {
    List<Cooking> list = cookingService.all();
    if (list != null) {
      return new ResponseEntity<>(list, HttpStatus.OK);
    }
    return ResponseEntity.notFound().build();
  }


  @RequestMapping(value = "category/{category}", method = RequestMethod.GET)
  public ResponseEntity category(@PathVariable String category) {
    List<Cooking> list = cookingService.category(category);
    if (list != null) {
      return new ResponseEntity<>(list, HttpStatus.OK);
    }
    return ResponseEntity.notFound().build();
  }

  /**
   * get top 6.
   */
  @RequestMapping(value = "top6", method = RequestMethod.GET)
  public ResponseEntity top6() {
    List<Cooking> cookings = cookingService.top6();
    if (cookings == null) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(cookings, HttpStatus.OK);
  }

  /**
   * 返回某一菜谱的所有作品.
   */
  @RequestMapping(value = "/{cookingId}/show", method = RequestMethod.GET)
  public ResponseEntity allShow(@PathVariable long cookingId) {
    List<Show> list = showService.findByCookingId(cookingId);
    if (list != null) {
      return new ResponseEntity<>(list, HttpStatus.OK);
    }
    return ResponseEntity.notFound().build();
  }

  /**
   * 返回某一菜谱的某一作品.
   */
  @RequestMapping(value = "{cookingId}/show/{showId}", method = RequestMethod.GET)
  public ResponseEntity oneShow(@PathVariable long cookingId, @PathVariable long showId) {
    Show show = showService.findById(showId);
    if (show == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(show);
  }

  /**
   * 为某一菜谱添加作品.
   */
  @RequestMapping(value = "{cookingId}/show/{showId}", method = RequestMethod.PUT)
  @Authorization
  public ResponseEntity addShowToCooking(@PathVariable long cookingId, @PathVariable long showId) {
    int count = showService.addShowToCooking(cookingId, showId);
    if (count > 0) {
      return ResponseEntity.ok("添加成功");
    }
    return ResponseEntity.ok("添加失败");
  }

  /**
   * 返回指定id的菜谱.
   *
   * @param cookingId 菜谱id.
   * @return 指定id 的菜谱.
   */
  @RequestMapping(value = "{cookingId}", method = RequestMethod.GET)
  public ResponseEntity cookingId(@PathVariable long cookingId) {
    Cooking cooking = cookingService.findById(cookingId);

    if (cooking == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(cooking);
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
  @RequestMapping(value = "{cookingId}", method = RequestMethod.PUT)
  @Authorization({Role.USER})
  public ResponseEntity update(@PathVariable long cookingId, @RequestBody Cooking cooking) {
    cooking.setCookingId(cookingId);
    int count = cookingService.updateCooking(cooking);
    if (count == 1) {
      return ResponseEntity.ok(cooking);
    }
    return ResponseEntity.ok("更新失败");
  }

  /**
   * 删除指定id 的菜谱.
   *
   * @param cookingId 菜谱id
   * @return 删除的菜谱信息.
   */
  @RequestMapping(value = "{cookingId}", method = RequestMethod.DELETE)
  @Authorization({Role.ADMIN, Role.USER})
  public ResponseEntity markDelete(@PathVariable long cookingId) {
    int count = cookingService.markCookingDelete(cookingId);
    if (count == 1) {
      return ResponseEntity.ok("删除成功");
    }
    return ResponseEntity.ok("删除失败");
  }

  /**
   * 用户点赞或者取消点赞. 对某一菜谱点赞或取消赞
   */
  @RequestMapping(value = "like", method = RequestMethod.POST)
  @Authorization({Role.USER})
  public ResponseEntity likeIt(@RequestBody CookingLikeForm cookingLikeForm) {
    CookingLike cookingLike = new CookingLike();
    cookingLike.setCookingId(cookingLikeForm.getCookingId());
    cookingLike.setUserId(cookingLikeForm.getUserId());
    /**
     * 根据like的值判断是点赞还是取消点赞
     */
    if (cookingLikeForm.getLike() > 0) {
      cookingService.addCookingLikeUser(cookingLike);
      cookingService.likeNumIncr(cookingLike.getCookingId());
      return ResponseEntity.ok("已点赞");
    }
    cookingService.deleteCookingLike(cookingLike.getUserId(), cookingLike.getCookingId());
    cookingService.likeNumDecr(cookingLike.getCookingId());
    return ResponseEntity.ok("已取消点赞");
  }


  private static class CookingLikeForm implements Serializable {
    private long cookingId;
    private long userId;
    private int like;

    public CookingLikeForm() {
    }

    public long getCookingId() {
      return cookingId;
    }

    public void setCookingId(long cookingId) {
      this.cookingId = cookingId;
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

