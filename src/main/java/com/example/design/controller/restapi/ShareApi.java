package com.example.design.controller.restapi;

import com.example.design.model.Message;
import com.example.design.service.impl.MessageService;
import com.example.design.model.Share;
import com.example.design.service.impl.ShareService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * share rest api.
 *
 * @author lxh
 * @version 0.1
 */
@RestController
@RequestMapping("api/share")
public class ShareApi {
  @Autowired
  private MessageService messageService;
  private ShareService shareService;

  /**
   * 查找分享下的留言message
   *
   * @param id share id
   * @return message
   */
  @RequestMapping("{id}/message")
 // @Autowired("{Role.User}")
  public ResponseEntity getMessagesByShareId(@PathVariable long id) {
    List<Message> messages = messageService.getByShareId(id);
    if (messages == null) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(messages, HttpStatus.OK);
  }

    /**
     * 返回某一用户的所有分享.
     */
    @RequestMapping(value = "/{userId}/share", method = RequestMethod.GET)
//  @Authorization({Role.ADMIN, Role.USER, Role.GUEST, Role.LIMITED_USER})
    public ResponseEntity allShare(@PathVariable long userId) {
        List<Share> list = shareService.selectByUserId(userId);
        if (list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * 返回某一用户的某一个分享.
     */
    @RequestMapping(value = "/{userId}/share/{itemId},{shareType}", method = RequestMethod.GET)
//  @Authorization({Role.ADMIN, Role.USER, Role.GUEST, Role.LIMITED_USER})
    public ResponseEntity allShare(@PathVariable long userId,@PathVariable long itemId,@PathVariable String shareType) {
        Share share = shareService.selectByItemAndType(itemId,shareType);
        if (share!= null) {
            return  ResponseEntity.ok(share);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * 新添加菜谱.
     *
     * @param share 分享body.
     * @return 新添加的分享信息.
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
//  @Authorization({Role.USER})
    public ResponseEntity add(@RequestBody Share share) {
        /**
         * 创建菜单
         */
        int count = shareService.addShare(share);

        if (count == 1) {
            return ResponseEntity.ok(share);
        }
        return ResponseEntity.ok("添加失败");
    }

    /**
     * 更新已存在分享.
     *
     * @param share 分享body.
     * @return 更改的分享信息.
     */
    @RequestMapping(value = "{shareId}", method = RequestMethod.PUT)
//  @Authorization({Role.USER})share
    public ResponseEntity updateShare(@PathVariable long shareId, @RequestBody Share share) {
        share.setShareId(shareId);
        int count = shareService.updateShare(share);
        if (count == 1) {
            return ResponseEntity.ok(share);
        }
        return ResponseEntity.ok("更新失败");
    }

    /**
     * 删除指定id 的分享.
     *
     * @param shareId 分享id
     * @return 删除的分享信息.
     */
    @RequestMapping(value = "{shareId}", method = RequestMethod.DELETE)
//  @Authorization({Role.ADMIN, Role.USER})
    public ResponseEntity deleteShare(@PathVariable long shareId) {
        int count = shareService.deleteShareByShareId(shareId);
        if (count == 1) {
            return ResponseEntity.ok("删除成功");
        }
        return ResponseEntity.ok("删除失败");
    }
}

