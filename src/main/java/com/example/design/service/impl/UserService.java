package com.example.design.service.impl;

import com.example.design.constant.Role;
import com.example.design.mapper.UserMapper;
import com.example.design.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 提供用户信息的服务 Created by lxh on 4/14/16.
 */
@Service
public class UserService implements UserDetailsService {
  @Autowired
  UserMapper mapper;

  /**
   * select all users.
   *
   * @return List
   */
  public List<User> all() {
    return mapper.all();
  }

  /**
   * select user by userId.
   *
   * @param userId user's id
   * @return User
   */
  public User id(long userId) {
    return mapper.id(userId);
  }

  /**
   * add a new user to db.
   *
   * @param user user to add.
   * @return number of rows affected.
   */
  public int addUser(User user) {
    return mapper.add(user);
  }

  /**
   * update user info.
   *
   * @param user user to update.
   * @return number of rows affected.
   */
  public int updateInfo(User user) {
    return mapper.update(user);
  }

  /**
   * select a user by account.
   *
   * @param name account name.
   * @return User.
   */
  public User getByAccountName(String name) {
    return mapper.selectByAccountName(name);
  }

  /**
   * remove a user by userId.
   *
   * @param userId user's id.
   * @return number of rows affected.
   */
  public int removeById(int userId) {
    return mapper.delete(userId);
  }

  /**
   * remove a user by account.
   */
  public int removeByAccountName(String account) {
    return mapper.deleteByAccountName(account);
  }

  /**
   * get Role of a user.
   */
  public Role getRole(String name) {
    return mapper.getRole(name);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = getByAccountName(username);
    if (user == null) {
      throw new UsernameNotFoundException("");
    }
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
    System.out.println(1 + user.getRole().name());
    return new org.springframework.security.core.userdetails.User(username,
            user.getPassword(), authorities);
  }
}
