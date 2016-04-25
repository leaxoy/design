package com.example.design.model;

import com.example.design.constant.Role;

import org.springframework.data.annotation.Id;

import java.util.Date;

import lombok.Data;


/**
 * 用户模型 Created by lxh on 4/14/16.
 */
@Data
public class User {
  @Id
  private Long userId;            // 用户ID
  private String account;         // 账户名称
  private String password;        // 用户密码
  private String email;           // 邮箱
  private String nickName;        // 昵称
  private String userPicture;     // 用户头像
  private String name;            // 真实姓名
  private String gender;          // 性别
  private Date birth;             // 出生日期
  private String job;             // 工作
  private String city;            // 居住城市
  private String userIntro;       // 个人介绍
  private Role role;              // 用户权限
  private int state;
}
