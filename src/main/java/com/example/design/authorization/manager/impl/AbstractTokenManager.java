package com.example.design.authorization.manager.impl;

import com.example.design.exception.MethodNotSupportException;
import com.example.design.authorization.manager.TokenManager;

/**
 * Token管理的基础类
 * Created by lxh on 4/20/16.
 */
public abstract class AbstractTokenManager implements TokenManager {

    protected int tokenExpireSeconds = 3600;

    protected boolean singleTokenWithUser = true;

    protected boolean flushExpireAfterOperation = true;


    public void setTokenExpireSeconds(int tokenExpireSeconds) {
        this.tokenExpireSeconds = tokenExpireSeconds;
    }

    public void setSingleTokenWithUser(boolean singleTokenWithUser) {
        this.singleTokenWithUser = singleTokenWithUser;
    }

    public void setFlushExpireAfterOperation(boolean flushExpireAfterOperation) {
        this.flushExpireAfterOperation = flushExpireAfterOperation;
    }

    public String getToken(String key) {
        String token = getTokenByKey(key);
        //根据设置，在每次有效操作后刷新过期时间
        if (token != null && flushExpireAfterOperation) {
            flushExpireAfterOperation(key, token);
        }
        return token;
    }

    public String getKey(String token) {
        String key = getKeyByToken(token);
        if (key != null && flushExpireAfterOperation) {
            flushExpireAfterOperation(key, token);
        }
        return key;
    }

    /**
     * 通过Token获得Key
     *
     * @param token
     * @return
     */
    protected abstract String getKeyByToken(String token);


    /**
     * 通过Key获得Token
     *
     * @param key
     * @return
     */
    protected abstract String getTokenByKey(String key);

    /**
     * 在操作后刷新Token的过期时间
     *
     * @param key
     * @param token
     */
    protected abstract void flushExpireAfterOperation(String key, String token);


    public void createToken(String key, String token) {
        //根据设置的每个用户是否只允许绑定一个Token，调用不同的方法
        if (singleTokenWithUser) {
            createSingleRelationship(key, token);
        } else {
            createMultipleRelationship(key, token);
        }
    }

    /**
     * 一个用户可以绑定多个Token时创建关联关系
     *
     * @param key
     * @param token
     */
    protected abstract void createMultipleRelationship(String key, String token);

    /**
     * 一个用户只能绑定一个Token时创建关联关系
     *
     * @param key
     * @param token
     */
    protected abstract void createSingleRelationship(String key, String token);

    public void updateToken(String key, String token) {

    }

    public void deleteToken(String key) {
        //如果是多个Token关联同一个Key，不允许直接通过Key删除所有Token，防止误操作
        if (!singleTokenWithUser) {
            throw new MethodNotSupportException("非单点登录时无法调用该方法");
        }
        delSingleRelationshipByKey(key);
    }

    /**
     * 一个用户只能绑定一个Token时通过Key删除关联关系
     *
     * @param key
     */
    protected abstract void delSingleRelationshipByKey(String key);
}
