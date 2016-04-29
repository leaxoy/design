package com.example.design.controller.restapi;

import com.example.design.model.Message;
import com.example.design.service.impl.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    /**
     * 新建留言
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity addMessage(@RequestBody Message message) {
        /**
         * 新建留言
         */
        int count = messageService.addMessage(message);
        if (1 == count) {
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.ok("发送失败");
    }

    @RequestMapping(value = "{messageId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteMessage(@PathVariable long messageId) {
        /**
         * 删除留言
         */
        int count = messageService.deleteMessage(messageId);
        if (1 == count) {
            return ResponseEntity.ok("删除成功");
        }
        return ResponseEntity.ok("修改失败");
    }

    /**
     * 获取留言信息
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
