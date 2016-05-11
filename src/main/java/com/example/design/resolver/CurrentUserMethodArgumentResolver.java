package com.example.design.resolver;

import com.example.design.annotation.CurrentUser;
import com.example.design.constant.TokenConstant;
import com.example.design.model.User;
import com.example.design.service.impl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * 增加方法注入，将含有CurrentUser注解的方法参数注入当前登录用户.
 *
 * @author lxh
 * @version 0.1
 * @see org.springframework.web.method.support.HandlerMethodArgumentResolver
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

  @Autowired
  private UserService userService;

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    //如果参数类型是User并且有CurrentUser注解则支持
    return parameter.getParameterType().isAssignableFrom(User.class)
            && parameter.hasParameterAnnotation(CurrentUser.class);
  }

  @Override
  public Object resolveArgument(
          MethodParameter parameter,
          ModelAndViewContainer mavContainer,
          NativeWebRequest webRequest,
          WebDataBinderFactory binderFactory) throws Exception {
    //取出鉴权时存入的登录用户Id
    String accountName = (String) webRequest.getAttribute(TokenConstant.CURRENT_USER_ID,
            RequestAttributes.SCOPE_REQUEST);
    if (accountName != null) {
      //从数据库中查询并返回
      return userService.getByAccountName(accountName);
    }
    throw new MissingServletRequestPartException(TokenConstant.CURRENT_USER_ID);
  }
}
