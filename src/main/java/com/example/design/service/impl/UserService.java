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
  UserMapper userMapper;

  /**
   * select all users.
   *
   * @return List
   */
  public List<User> all() {
    return userMapper.all();
  }

  /**
   * select user by userId.
   *
   * @param userId user's id
   * @return User
   */
  public User id(long userId) {
    return userMapper.id(userId);
  }

  /**
   * add a new user to db.
   *
   * @param user user to add.
   * @return number of rows affected.
   */
  public int addUser(User user) {
    return userMapper.add(user);
  }

  /**
   * update user info.
   *
   * @param user user to update.
   * @return number of rows affected.
   */
  public int updateInfo(User user) {
    return userMapper.update(user);
  }

  /**
   * select a user by account.
   *
   * @param name account name.
   * @return User.
   */
  public User getByAccountName(String name) {
    return userMapper.selectByAccountName(name);
  }

  /**
   * remove a user by userId.
   *
   * @param userId user's id.
   * @return number of rows affected.
   */
  public int removeById(int userId) {
    return userMapper.delete(userId);
  }

  /**
   * remove a user by account.
   */
  public int removeByAccountName(String account) {
    return userMapper.deleteByAccountName(account);
  }

  /**
   * get Role of a user.
   */
  public Role getRole(String name) {
    return userMapper.getRole(name);
  }

  /**
   * mark a user to limited.
   *
   * @param userId user's id.
   */
  public int markLimit(Long userId) {
    return userMapper.markLimit(userId);
  }

  /**
   * mark a user unlimited.
   *
   * @param userId user's id.
   */
  public int markNormal(Long userId) {
    return userMapper.markNormal(userId);
  }

  /**
   * change password.
   */
  public int updatePassword(String account, String password) {
    return userMapper.updatePassword(account, password);
  }

  /**
   * get users by nickName.
   */
  public List<User> getByNickName(String nickName) {
    return userMapper.selectByNickName(nickName);
  }

  /**
   * get users by city name.
   */
  public List<User> getByCity(String city) {
    return userMapper.selectByCity(city);
  }

  /**
   * get all limited users.
   */
  public List<User> getAllLimitUser() {
    return userMapper.findLimitUser();
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
