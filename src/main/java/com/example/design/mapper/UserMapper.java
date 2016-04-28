package com.example.design.mapper;

import com.example.design.constant.Role;
import com.example.design.model.User;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
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
@Repository
@Mapper
public interface UserMapper {
  @Insert("INSERT INTO `user`(`account`, `password`, `role`) VALUES"
          + "(#{account}, #{password}, #{role})")
  int add(User user);                   //添加用户

  @Update("UPDATE `user` SET `email`=#{email}, `nickName`=#{nickName}, `userPicture`="
          + "#{userPicture}, `name`=#{name}, `gender`=#{gender}, `birth`=#{birth}, `job`=#{job},"
          + "`city`=#{city}, `userIntro`=#{userIntro} WHERE `account`=#{account}")
  int update(User user);        //更新用户

  @Update("UPDATE `user` SET `password`=#{password} WHERE `account`=#{account}")
  int updatePassword(String account, String password);        //修改密码。

  @Update("UPDATE `user` SET `state`=1 WHERE `userId`=#{userId}")
  int markLimit(long userId);            //修改用户状态，表示受限。

  @Update("UPDATE `user` SET `state`=0 WHERE `userId`=#{userId}")
  int markNormal(long userId);            //修改用户状态，表示恢复正常。

  @Select("SELECT * FROM `user`")
  List<User> all();

  @Select("SELECT * FROM `user` WHERE `userId`=#{id}")
  User id(long id);                      //通过ID查找用户

  @Select("SELECT `userId`, `role`, `account`, `password`, `email`, `nickName`, `userPicture`, "
          + "`name`, `gender`, `birth`, `job`, `city`, `userIntro` FROM `user` WHERE "
          + "`account`=#{account}")
  User selectByAccountName(@Param("account") String account);      //通过用户账号查找用户

  @Select("SELECT * FROM `user` WHERE `nickName`=#{nickname}")
  List<User> selectByNickName(@Param("nickname") String nickname);   //通过用户昵称查找所有用户信息

  @Select("SELECT * FROM `user` WHERE `city`=#{city}")
  List<User> selectByCity(@Param("city") String city);               //通过用户城市查找用户所有信息

  @Select("SELECT `role` FROM `user` WHERE `account`=#{name}")
  @Results(value = {@Result(column = "role", property = "Role", javaType = Role.class,
          jdbcType = JdbcType.CHAR)})
  Role getRole(String name);                  //根据用户ID查用户状态

  @Select("SELECT * FROM `user` WHERE `state`=1")
  List<User> findLimitUser();         //查询所有受限用户

  @Delete("DELETE FROM `user` WHERE `id`=#{id}")
  int delete(int id);                 //删除用户通过ID

  @Delete("DELETE FROM `user` WHERE `account`=#{account}")
  int deleteByAccountName(@Param("account") String account);  //通过账号删除用户
}
