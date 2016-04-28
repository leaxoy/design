package com.example.design.constant;

/**
 * Created by lxh on 16/4/27.
 */
public enum RequestEvent {
  AUTH(1000),
  SIGN_UP(1001),
  LIKE(1100),
  UN_LINK(1101),
  FOLLOW(1200),
  UN_FOLLOW(1201),
  PUB_MENU(1300),
  PUB_COOKING(1301),
  PUB_SHOW(1302),

  GET_MENU(1400),
  GET_COOKING(1401),
  GET_LIKE_INFO(1402);


  RequestEvent(int message) {
  }
}
