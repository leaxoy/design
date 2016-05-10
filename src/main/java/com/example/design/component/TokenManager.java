package com.example.design.component;

import com.example.design.component.model.TokenModel;

/**
 * Token manager interface.
 *
 * @author lxh
 * @version 0.1
 */
public interface TokenManager {
  TokenModel get(String key);

  void clear(String key);

  TokenModel verify(String tokenStr);

  TokenModel generateToken(TokenModel model);
}
