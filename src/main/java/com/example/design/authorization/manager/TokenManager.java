package com.example.design.authorization.manager;

import com.example.design.authorization.model.AuthToken;

/**
 * 对Token进行管理的接口
 * Created by lxh on 4/20/16.
 */
public interface TokenManager {

    /**
     * 创建一个token关联上指定用户
     *
     * @param accountName 指定用户的accountName
     * @return 生成的token
     */
    AuthToken createToken(String accountName);

    /**
     * 检查token是否有效
     *
     * @param model token
     * @return 是否有效
     */
    boolean checkToken(AuthToken model);

    /**
     * 从字符串中解析token
     *
     * @param authentication 加密后的字符串
     * @return 获取到的token
     */
    AuthToken getToken(String authentication);

    /**
     * 清除token
     *
     * @param accountName 登录用户的id
     */
    void deleteToken(String accountName);

}

