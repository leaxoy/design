package com.example.design.mapper;

import com.example.design.constant.Role;
import com.example.design.model.User;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户持久化接口. Created by lxh on 4/14/16.
 */
@Repository("userMapper")
public interface UserMapper {
  @Insert("INSERT INTO `user`(`account`, `password`, `role`) VALUES"
          + "(#{account}, #{password}, #{role})")
  int add(User user);

  @Update("UPDATE `user` () VALUES() WHERE `id`=#{id}")
  int update(User user);

  @Select("SELECT * FROM `user`")
  List<User> all();

  @Select("SELECT `userId`, `role`, `account`, `password`, `email`, `nickName`, `userPicture`, "
          + "`name`, `gender`, `birth`, `job`, `city`, `userIntro` FROM `user` WHERE "
          + "`account`=#{account}")
  User selectByAccountName(@Param("account") String account);

  @Select("SELECT * FROM `user` WHERE `nickName`=#{nickname}")
  List<User> selectByNickName(@Param("nickname") String nickname);

  @Select("SELECT `role` FROM `user` WHERE `account`=#{name}")
  @Results(value = {@Result(column = "role", property = "Role", javaType = Role.class,
          jdbcType = JdbcType.CHAR)})
  Role getRole(String name);

  @Delete("DELETE FROM `user` WHERE `id`=#{id}")
  int delete(int id);

  @Delete("DELETE FROM `user` WHERE `account`=#{account}")
  int deleteByAccountName(@Param("account") String account);
}
