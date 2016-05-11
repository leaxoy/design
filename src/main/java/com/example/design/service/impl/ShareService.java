package com.example.design.service.impl;

import com.example.design.mapper.ShareMapper;
import com.example.design.model.Share;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 分享作品的服务接口 Created by lxh on 4/20/16.
 */
@Service
@Transactional
public class ShareService {
  @Autowired
  private ShareMapper shareMapper;

  public int addShare(Share share) {
    return shareMapper.add(share);
  }

  public int updateShare(Share share) {
    return shareMapper.update(share);
  }

  public int deleteShareByShareId(Share share) {
    return shareMapper.delete(share);
  }

  public List<Share> selectByItemAndType(long itemId, String shareType) {
    return shareMapper.findByItemAndType(itemId, shareType);
  }

  public List<Share> selectByUserId(long shareUserId) {
    return shareMapper.findByUserId(shareUserId);
  }

}
