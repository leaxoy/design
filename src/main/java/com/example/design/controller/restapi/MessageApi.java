package com.example.design.controller.restapi;

import com.example.design.model.Message;
import com.example.design.service.impl.MessageService;

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
 * message rest api.
 *
 * @author lxh
 * @version 0.1
 */
@RestController
@RequestMapping("api/message")
public class MessageApi {
  @Autowired
  private MessageService messageService;

  /**
   * 获取用户发布的留言
   *
   * @param id user id.
   * @return user.
   */
  @RequestMapping(value = "{id}")
  public ResponseEntity allMessage(@PathVariable long id) {
    List<Message> messages = messageService.getByUserId(id);
    if (messages == null) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(messages, HttpStatus.OK);
  }

  /**
   * 新建留言.
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity addMessage(@RequestBody Message message) {
    int count = messageService.addMessage(message);
    if (1 == count) {
      return ResponseEntity.ok(message);
    }
    return ResponseEntity.ok("发送失败");
  }

  /**
   * 删除留言.
   *
   * @param messageId 留言ID
   * @return 是否删除成功.
   */
  @RequestMapping(value = "{messageId}", method = RequestMethod.DELETE)
  public ResponseEntity deleteMessage(@PathVariable long messageId) {
    int count = messageService.deleteMessage(messageId);
    if (1 == count) {
      return ResponseEntity.ok("删除成功");
    }
    return ResponseEntity.ok("修改失败");
  }

  /**
   * 获取留言信息.
   *
   * @param messageId message id
   * @return message
   */
  @RequestMapping(value = "{messageId}", method = RequestMethod.GET)
  public ResponseEntity changeInfo(@PathVariable long messageId) {
    Message message = messageService.getByMessageId(messageId);
    if (message == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(message);
  }
}
