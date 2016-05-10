package com.example.design.component;

import com.example.design.component.model.TokenModel;

/**
 * Created by lxh on 16/5/10.
 */
public interface TokenManager {
  TokenModel get(String key);

  void clear(String key);

  TokenModel verify(String tokenStr);

  TokenModel generateToken(TokenModel model);
}
