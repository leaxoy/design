package com.example.design.manager;

/**
 * 对Token进行管理的接口
 * Created by lxh on 4/20/16.
 */
public interface TokenManager {

    String getToken(String key);

    String getKey(String token);

    void createToken(String key, String token);

    void deleteToken(String key);
}
