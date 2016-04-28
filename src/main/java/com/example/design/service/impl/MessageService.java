package com.example.design.service.impl;

import com.example.design.mapper.MessageMapper;
import com.example.design.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 留言的服务接口 Created by lxh on 4/14/16.
 */
@Service
public class MessageService {
    @Autowired
    MessageMapper mapper;

    public int addMessage(Message message) {
        return mapper.addMessage(message);         //新建留言
    }

    public int deleteMessage(long messageId) {
        return mapper.markDelete(messageId);       //删除留言
    }

    public int deleteMessageByshareId(long shareId) {
        return mapper.markDeleteByshareId(shareId);       //根据分享删除留言
    }

    public int deleteMessageByshowId(long showId) {
        return mapper.markDeleteByshowId(showId);       //根据作品删除留言；
    }

    public List<Message> getBymessageUserId(long messageUserId) {
        return mapper.selectBymessageUserId(messageUserId);          //查询用户所发所有留言
    }

    public List<Message> getByUserId(long userId) {
        return mapper.selectByuserId(userId);                        //查询用户所收所有留言
    }

    public List<Message> getByShareId(long shareId) {
        return mapper.selectByshareId(shareId);                       //查询分享下所有留言
    }

    public List<Message> getByShoweId(long showId) {
        return mapper.selectByshowId(showId);                        //查询作品下所有留言
    }
}
