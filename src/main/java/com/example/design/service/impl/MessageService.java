package com.example.design.service.impl;

import com.example.design.mapper.MessageMapper;
import com.example.design.model.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 留言的服务接口 Created by lxh on 4/14/16.
 */
@Service
@Transactional
public class MessageService {
  @Autowired
  private MessageMapper mapper;

  public int addMessage(Message message) {
    return mapper.add(message);         //新建留言
  }

  public int deleteMessage(long messageId) {
    return mapper.markDeleted(messageId);       //删除留言
  }

  public int deleteMessageByShareId(long shareId) {
    return mapper.markDeletedByShareId(shareId);       //根据分享删除留言
  }

  public int deleteMessageByShowId(long showId) {
    return mapper.markDeletedByShowId(showId);       //根据作品删除留言；
  }

  public List<Message> getByMessageUserId(long messageUserId) {
    return mapper.findByMessageUserId(messageUserId);          //查询用户所发所有留言
  }

  public List<Message> getByUserId(long userId) {
    return mapper.findByUserId(userId);                        //查询用户所收所有留言
  }

  public List<Message> getByShareId(long shareId) {
    return mapper.findByShareId(shareId);                       //查询分享下所有留言
  }

  public List<Message> getByShowId(long showId) {
    return mapper.findByShowId(showId);                        //查询作品下所有留言
  }

  public Message getByMessageId(long messageId) {
    return mapper.findByMessageId(messageId);
  }
}
