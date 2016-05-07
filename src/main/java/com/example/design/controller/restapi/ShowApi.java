package com.example.design.controller.restapi;

import com.example.design.model.Message;
import com.example.design.model.Show;
import com.example.design.model.ShowLike;
import com.example.design.service.impl.MessageService;
import com.example.design.service.impl.ShowService;

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
 * show rest api.
 *
 * @author lxh
 * @version 0.1
 */
@RestController
@RequestMapping("api/show")
public class ShowApi {

  /**
   * articleService DI.
   */
  @Autowired
  private ShowService showService;

  @Autowired
  private MessageService messageService;

  /**
   * 返回所有作品.
   *
   * @return all articles list.
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
//  @Authorization({Role.ADMIN, Role.USER, Role.GUEST, Role.LIMITED_USER})
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
   * @param showId 作品id.
   * @return 指定id 的作品.
   */
  @RequestMapping(value = "{showId}", method = RequestMethod.GET)
//  @Authorization({Role.ADMIN, Role.USER, Role.GUEST, Role.LIMITED_USER})
  public ResponseEntity showId(@PathVariable long showId) {
    Show show = showService.findShowById(showId);

    if (show == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(show);
  }


  /**
   * 新添加作品.
   *
   * @param show 作品body.
   * @return 新添加的作品信息.
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
//  @Authorization({Role.USER})
  public ResponseEntity add(@RequestBody Show show) {
    int count = showService.addShow(show);
    if (count > 0) {
      return ResponseEntity.ok(show);
    }
    return ResponseEntity.ok("添加失败");
  }

  /**
   * 更新已存在作品.
   *
   * @param show 作品body.
   * @return 更改的作品信息.
   */
  @RequestMapping(value = "{showId}", method = RequestMethod.PUT)
//  @Authorization({Role.USER})
  public ResponseEntity update(@PathVariable long showId, @RequestBody Show show) {
    show.setShowId(showId);
    int count = showService.updateShow(show);
    if (count > 0) {
      return ResponseEntity.ok(show);
    }
    return ResponseEntity.ok("更新失败");
  }

  /**
   * 删除指定id 的作品.
   *
   * @param showId 作品id
   * @return 删除的作品信息.
   */
  @RequestMapping(value = "{showId}", method = RequestMethod.DELETE)
  //  @Authorization({Role.ADMIN, Role.USER})
  public ResponseEntity markDelete(@PathVariable long showId) {
    int count = showService.markShowDelete(showId);
    if (count > 0) {
      return ResponseEntity.ok("删除成功");
    }
    return ResponseEntity.ok("删除失败");
  }

  /**
   * 对某一作品点赞或取消赞
   */
  @RequestMapping(value = "like", method = RequestMethod.POST)
//  @Authorization({Role.USER})
  public ResponseEntity likeIt(@RequestBody ShowLikeForm showLikeForm) {
    ShowLike showLike = new ShowLike();
    showLike.setShowId(showLikeForm.getShowId());
    showLike.setUserId(showLikeForm.getUserId());
    /**
     * 根据like的值判断是点赞还是取消点赞
     */
    if (showLikeForm.getLike() > 0) {
      showService.addShowLikeUser(showLike);
      showService.likeNumIncr(showLike.getShowId());
      return ResponseEntity.ok("点赞成功");
    }
    showService.deleteShowLike(showLike.getUserId(), showLike.getShowId());
    showService.likeNumDecr(showLike.getShowId());
    return ResponseEntity.ok("取消点赞");
  }

  /**
   * 查找作品下的留言message
   *
   * @param id show id
   * @return message
   */
  @RequestMapping("{id}/message")
  public ResponseEntity getMessagesByShowId(@PathVariable long id) {
    List<Message> messages = messageService.getByShowId(id);
    if (messages == null) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(messages, HttpStatus.OK);
  }


  public static class ShowLikeForm {
    private long showId;
    private long userId;
    private int like;

    public ShowLikeForm(long showId, long userId, int like) {
      this.showId = showId;
      this.userId = userId;
      this.like = like;
    }

    public ShowLikeForm() {

    }

    public long getShowId() {
      return showId;
    }

    public long getUserId() {
      return userId;
    }

    public int getLike() {
      return like;
    }

  }
}
