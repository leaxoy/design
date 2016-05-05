package com.example.design.configuration;

import com.example.design.service.impl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 对 spring security 的一些配置. Created by lxh on 4/13/16.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

  /**
   * 用户信息服务.
   */
  @Autowired
  private UserService userService;

  @Override
  protected final void configure(final HttpSecurity http) throws Exception {
    http.csrf().disable();
  }

  @Override
  protected final void configure(final AuthenticationManagerBuilder auth)
          throws Exception {
    auth.userDetailsService(userService);
  }
}
