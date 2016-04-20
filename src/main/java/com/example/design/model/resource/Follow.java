package com.example.design.model.resource;

import org.springframework.data.annotation.Id;

/**
 * 用户关系模型
 * Created by lxh on 4/14/16.
 */
public class Follow {
    @Id
    private Long followId;
    private Long userId;
    private Long friendId;

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFollowId() {
        return followId;
    }

    public void setFollowId(Long followId) {
        this.followId = followId;
    }
}
