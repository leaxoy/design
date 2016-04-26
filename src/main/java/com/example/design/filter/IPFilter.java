package com.example.design.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * IP 过滤器. Created by lxh on 4/13/16.
 */
@Configuration
public class IPFilter implements Filter {

  private static final Logger LOGGER = LoggerFactory.getLogger(IPFilter.class);

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    LOGGER.info(req.getRemoteAddr());
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {

  }
}
