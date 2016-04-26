package com.example.design.constant;

/**
 * 用户组的枚举类型 Created by lxh on 4/18/16.
 */
public enum Role {
  ROOT(1),                // 超级管理员
  ADMIN(1 << 1),          // 普通管理员
  USER(1 << 2),           // 普通用户
  LIMITED_USER(1 << 3),   // 受限制的用户
  GUEST(1 << 4);          // 游客

  Role(int level) {
  }
}
