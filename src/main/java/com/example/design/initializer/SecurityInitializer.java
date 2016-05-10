package com.example.design.initializer;

import com.example.design.configuration.AppRedisConfig;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * Created by lxh on 16/5/10.
 */
public class SecurityInitializer extends AbstractHttpSessionApplicationInitializer {
  public SecurityInitializer() {
    super(AppRedisConfig.class);
  }
}
