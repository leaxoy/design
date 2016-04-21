package com.example.design.authorization.manager.impl;

import com.example.design.authorization.manager.TokenManager;
import com.example.design.authorization.model.AuthToken;
import com.example.design.constant.TokenConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 使用Redis存储Token
 * Created by lxh on 4/20/16.
 */
@Component
public class RedisTokenManager implements TokenManager {

    private RedisTemplate<String, String> redis;

    @Autowired
    public void setRedis(RedisTemplate<String, String> redisTemplate) {
        this.redis = redisTemplate;
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    @Override
    public AuthToken createToken(String accountName) {
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        AuthToken model = new AuthToken(accountName, token);
        //存储到redis并设置过期时间
        redis.boundValueOps(accountName).set(token, TokenConstant.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return model;
    }

    @Override
    public boolean checkToken(AuthToken model) {
        if (model == null) {
            return false;
        }
        String token = redis.boundValueOps(model.getAccountName()).get();
        if (token == null || !token.equals(model.getTokenContent())) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redis.boundValueOps(model.getAccountName()).expire(TokenConstant.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
    }

    @Override
    public AuthToken getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        //使用accountName和源token简单拼接成的token，可以增加加密措施
        String accountName = param[0];
        String token = param[1];
        return new AuthToken(accountName, token);
    }

    @Override
    public void deleteToken(String accountName) {
        redis.delete(accountName);
    }
}
