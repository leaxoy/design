package com.example.design.authorization.interceptor;

import com.example.design.authorization.annotation.Authorization;
import com.example.design.authorization.manager.impl.RedisTokenManager;
import com.example.design.authorization.model.AuthToken;
import com.example.design.constant.TokenConstant;
import com.example.design.constant.UserRole;
import com.example.design.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自定义拦截器，对请求进行身份验证
 * Created by lxh on 4/20/16.
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private UserService userService;
    //管理身份验证操作的对象
    @Autowired
    private RedisTokenManager manager;

    //鉴权信息的无用前缀，默认为空
    private String httpHeaderPrefix = "";

    //鉴权失败后返回的错误信息，默认为401 unauthorized
    private String unauthorizedErrorMessage = "401 unauthorized";

    //鉴权失败后返回的HTTP错误码，默认为401
    private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;

    public void setHttpHeaderPrefix(String httpHeaderPrefix) {
        this.httpHeaderPrefix = httpHeaderPrefix;
    }

    public void setUnauthorizedErrorMessage(String unauthorizedErrorMessage) {
        this.unauthorizedErrorMessage = unauthorizedErrorMessage;
    }

    public void setUnauthorizedErrorCode(int unauthorizedErrorCode) {
        this.unauthorizedErrorCode = unauthorizedErrorCode;
    }

    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //从header中得到token
        String tokenValue = request.getHeader(TokenConstant.AUTHORIZATION);
        if (tokenValue != null && tokenValue.startsWith(httpHeaderPrefix) && tokenValue.length() > 0) {
            tokenValue = tokenValue.substring(httpHeaderPrefix.length());
            //验证token
            AuthToken token = manager.getToken(tokenValue);
            if (token != null && manager.checkToken(token)) {
                //如果token验证成功，将token对应的用户id存在request中，便于之后注入
                request.setAttribute(TokenConstant.CURRENT_USER_ID, token.getAccountName());

                Authorization authorization = handlerMethod.getMethod().getAnnotation(Authorization.class);
                if (authorization != null) {
                    List<UserRole> userRoles = new ArrayList<>(Arrays.asList(authorization.value()));
                    if (userRoles.size() != 0) {
                        UserRole userRole = userService.getRole(token.getAccountName());
                        return userRoles.contains(userRole);
                    }
                }
                return true;
            }
        }

        //如果验证token失败，并且方法注明了Authorization，返回401错误
        if (method.getAnnotation(Authorization.class) != null   //查看方法上是否有注解
                || handlerMethod.getBeanType().getAnnotation(Authorization.class) != null) {    //查看方法所在的Controller是否有注解
            response.setStatus(unauthorizedErrorCode);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
            writer.write(unauthorizedErrorMessage);
            writer.close();
            return false;
        }

        //为了防止以恶意操作直接在REQUEST_CURRENT_KEY写入key，将其设为null
        request.setAttribute(TokenConstant.CURRENT_USER_ID, null);
        return true;
    }
}
