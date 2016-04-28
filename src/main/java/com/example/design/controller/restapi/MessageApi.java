package com.example.design.controller.restapi;

import com.example.design.service.impl.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
