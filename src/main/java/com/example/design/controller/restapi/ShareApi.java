package com.example.design.controller.restapi;

import com.example.design.model.Message;
import com.example.design.service.impl.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 查找分享下的留言message
     *
     * @param id share id
     * @return message
     */
    @RequestMapping("{id}/message")
    public ResponseEntity getMessagesByShareId(@PathVariable long id) {
        List<Message> messages = messageService.getByShareId(id);
        if (messages == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
