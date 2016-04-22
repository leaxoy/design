package com.example.design.mapper;

import com.example.design.constant.UserRole;
import com.example.design.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户持久化接口.
 * Created by lxh on 4/14/16.
 */
@Repository("userMapper")
public interface UserMapper {
    @Insert("INSERT INTO `user` () VALUES ()")
    int add(User user);

    @Update("UPDATE `user` () VALUES() WHERE `id`=#{id}")
    int update(User user);

    @Select("SELECT * FROM `user`")
    List<User> all();

    @Select("SELECT `id`, `role`, `accountName`, `password`, `email`, `nickName`, `userPicture`, `name`, `sex`, `birthday`, `job`, `city`, `userIntro` FROM `user` WHERE `accountName`=#{name}")
    User selectByAccountName(@Param("name") String name);

    @Select("SELECT * FROM `user` WHERE `nickName`=#{nickname}")
    List<User> selectByNickName(@Param("nickname") String nickname);

    @Select("SELECT `role` FROM `user` WHERE `accountName`=#{name}")
    @Results(value = {@Result(column = "role", property = "UserRole", javaType = UserRole.class, jdbcType = JdbcType.CHAR)})
    UserRole getRole(String name);

    @Delete("DELETE FROM `user` WHERE `id`=#{id}")
    int delete(int id);

    @Delete("DELETE FROM `user` WHERE `accountName`=#{accountName}")
    int deleteByAccountName(@Param("accountName") String accountName);
}
