package com.example.design.authorization.manager;

import com.example.design.authorization.model.AuthToken;

/**
 * 对Token进行管理的接口
 * Created by lxh on 4/20/16.
 */
public interface TokenManager {

    /**
     * 通过 key 获取token
     *
     * @param key token 的key
     * @return token
     */
    String getToken(String key);

    /**
     * 通过 token 获取key
     *
     * @param token key 的token
     * @return key
     */
    String getKey(String token);

    /**
     * 通过key 生成新的 token
     *
     * @param key key
     * @return token
     */
    AuthToken createToken(String key);

    /**
     * 通过key 删除token
     *
     * @param key key
     */
    void deleteToken(String key);

    /**
     * 验证 token 的可用性
     *
     * @param token token
     * @return 可用true, 不可用false
     */
    boolean verifyToken(AuthToken token);
}
