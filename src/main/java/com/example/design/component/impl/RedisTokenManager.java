package com.example.design.component.impl;

import com.example.design.component.TokenManager;
import com.example.design.component.model.TokenModel;
import com.example.design.constant.TokenConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Redis token manager.
 *
 * @author lxh
 * @version 0.1
 */
@Component
public class RedisTokenManager implements TokenManager {

  @Autowired
  private RedisTemplate<String, TokenModel> redisTemplate;

  @Override
  public TokenModel get(String key) {
    return (TokenModel) redisTemplate.boundHashOps(key).get(key);
  }

  @Override
  public void clear(String key) {
    redisTemplate.delete(key);
  }

  @Override
  public TokenModel verify(String tokenStr) {
    Claims claims = Jwts.parser().setSigningKey(TokenConstant.JWT_SECRET_KEY)
            .parseClaimsJws(tokenStr).getBody();
    if (claims.getExpiration().compareTo(new Date()) < 0) {
      String account = claims.getIssuer();
      TokenModel model = (TokenModel) redisTemplate.boundHashOps(account).get(account);
      if (Objects.equals(model.getContent(), tokenStr)) {
        return model;
      }
    }
    return null;
  }

  @Override
  public TokenModel generateToken(TokenModel model) {
    String content = Jwts.builder().setIssuer(model.getAccount()).setExpiration(new
            Date(System.currentTimeMillis() + 3600 * 1000)).setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, TokenConstant.JWT_SECRET_KEY).compact();
    model.setContent(content);
    redisTemplate.boundHashOps(model.getAccount()).putIfAbsent(model.getAccount(), model);
    return model;
  }
}
