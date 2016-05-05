package com.example.design.service.impl;

import com.example.design.mapper.ShareMapper;
import com.example.design.model.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分享作品的服务接口 Created by lxh on 4/20/16.
 */
@Service
public class ShareService {
    @Autowired
    private ShareMapper shareMapper;

    public int addShare(Share share) { return shareMapper.addShare(share); }

    public int updateShare(Share share) { return shareMapper.updateShare(share); }

    public int deleteShareByShareId(long shareId) { return shareMapper.deleteByShareId(shareId); }

    public Share selectByItemAndType(long itemId, String shareType) {
        return shareMapper.selectByItemAndType(itemId, shareType);
    }

    public List<Share> selectByUserId(long shareUserId){ return shareMapper.selectByUserId(shareUserId); }

}
