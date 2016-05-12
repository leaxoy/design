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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 提供用户信息的服务 Created by lxh on 4/14/16.
 */
@Service
@Transactional
public class UserService implements UserDetailsService {
  @Autowired
  private UserMapper userMapper;

  /**
   * select findAll users.
   *
   * @return List
   */
  public List<User> all() {
    return userMapper.findAll();
  }

  /**
   * select user by userId.
   *
   * @param userId user's findById
   * @return User
   */
  public User id(long userId) {
    return userMapper.findById(userId);
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
    return userMapper.findByAccount(name);
  }

  /**
   * remove a user by userId.
   *
   * @param userId user's findById.
   * @return number of rows affected.
   */
  public int removeById(User user) {
    return userMapper.delete(user);
  }

  /**
   * remove a user by account.
   */
  public int removeByAccountName(User user) {
    return userMapper.deleteByAccount(user);
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
   * @param userId user's findById.
   */
  public int markLimit(Long userId) {
    return userMapper.markLimited(userId);
  }

  /**
   * mark a user unlimited.
   *
   * @param userId user's findById.
   */
  public int markNormal(Long userId) {
    return userMapper.markNormal(userId);
  }

  /**
   * change password.
   */
  public int updatePassword(User user) {
    return userMapper.updatePassword(user);
  }

  /**
   * get users by nickName.
   */
  public List<User> getByNickName(String nickName) {
    return userMapper.findByNickName(nickName);
  }

  /**
   * get users by city name.
   */
  public List<User> getByCity(String city) {
    return userMapper.findByCity(city);
  }

  /**
   * get findAll limited users.
   */
  public List<User> getAllLimitUser() {
    return userMapper.findLimitedUser();
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = getByAccountName(username);
    if (user == null) {
      throw new UsernameNotFoundException("");
    }
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
    return new org.springframework.security.core.userdetails.User(username,
            user.getPassword(), authorities);
  }
}
