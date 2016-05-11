package com.example.design.service.impl;

import com.example.design.mapper.CommentMapper;
import com.example.design.model.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 评论服务
 *
 * @author lxh
 * @version 0.1
 */
@Service
@Transactional
public class CommentService {

  @Autowired
  private CommentMapper commentMapper;

  public List<Comment> all() {
    return commentMapper.queryAll();
  }

  public Comment id(long id) {
    return commentMapper.queryById(id);
  }

  public List<Comment> byUserId(long userId) {
    return commentMapper.queryByUserId(userId);
  }

  public int save(Comment comment) {
    return commentMapper.add(comment);
  }

  public int delete(Comment comment) {
    return commentMapper.delete(comment);
  }
}
