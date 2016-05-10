package com.example.design.interceptor;

import com.example.design.annotation.Authorization;
import com.example.design.component.impl.RedisTokenManager;
import com.example.design.component.model.TokenModel;
import com.example.design.constant.Role;
import com.example.design.constant.TokenConstant;
import com.example.design.service.impl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器，对请求进行身份验证 Created by lxh on 4/20/16.
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

  /**
   * userService 用户信息服务.
   */
  @Autowired
  private UserService userService;
  /**
   * redisTokenManager token管理接口.
   */
  @Autowired
  private RedisTokenManager redisTokenManager;


  /**
   * 鉴定权限信息的无用前缀，默认为空.
   */
  private String httpHeaderPrefix = "";
  /**
   * 鉴定权限失败后返回的错误信息，默认为401 unauthorized.
   */
  private String unauthorizedErrorMessage = "401 unauthorized";

  /**
   * 鉴定权限失败后返回的HTTP错误码，默认为401.
   */
  private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;

  /**
   * 设置 httpHeaderPrefix.
   *
   * @param httpHeaderPrefix 用户自定义的httpHeaderPrefix.
   */
  public final void setHttpHeaderPrefix(final String httpHeaderPrefix) {
    this.httpHeaderPrefix = httpHeaderPrefix;
  }

  /**
   * @param unauthorizedErrorMessage 用户自定义的信息.
   */
  public final void setUnauthorizedErrorMessage(
          final String unauthorizedErrorMessage) {
    this.unauthorizedErrorMessage = unauthorizedErrorMessage;
  }

  /**
   * 设置errorCode.
   *
   * @param unauthorizedErrorCode 用户自定义的code.
   */
  public final void setUnauthorizedErrorCode(
          final int unauthorizedErrorCode) {
    this.unauthorizedErrorCode = unauthorizedErrorCode;
  }

  /**
   * @param request  请求的对象.
   * @param response 回复的对象.
   * @param handler  拦截的方法对象.
   * @return 是否通过.
   * @throws Exception 可能抛出的异常.
   */
  @Override
  public final boolean preHandle(
          final HttpServletRequest request,
          final HttpServletResponse response,
          final Object handler) throws Exception {
    //如果不是映射到方法直接通过
    if (!(handler instanceof HandlerMethod)) {
      return true;
    }


    HandlerMethod handlerMethod = (HandlerMethod) handler;

    String tokenValue = request.getHeader(TokenConstant.AUTHORIZATION);
    if (tokenValue == null || !tokenValue.startsWith(TokenConstant.AUTHORIZATION)) {
      return false;
    }

    tokenValue = tokenValue.substring(httpHeaderPrefix.length());


    TokenModel tokenModel = redisTokenManager.verify(tokenValue);

    if (tokenModel == null) {
      unAuthorization(response);
      return false;
    }


    Authorization authorization = handlerMethod.getMethod().getAnnotation(Authorization.class);

    if (authorization == null) {
      return true;
    }

    List<Role> roles = Arrays.asList(authorization.value());

    if (roles.isEmpty()) {
      return false;
    }

    Role role = userService.getRole(tokenModel.getAccount());
    request.setAttribute(TokenConstant.CURRENT_USER_ID,
            userService.getByAccountName(tokenModel.getAccount()));
    return roles.contains(role);
  }

  private void unAuthorization(final HttpServletResponse response) throws IOException {
    response.setStatus(unauthorizedErrorCode);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(response.getOutputStream()));
    writer.write(unauthorizedErrorMessage);
    writer.close();
  }
}
