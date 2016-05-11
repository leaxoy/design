package com.example.design.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * friend mapper.
 *
 * @author lxh
 * @version 0.1
 */
@Repository
@Mapper
public interface FriendMapper {

  @Select("SELECT `friendId` FROM `friend` WHERE `userId`=#{userId}")
  List<Long> findByUserId(@Param("userId") long userId);

  @Insert("INSERT INTO `friend` VALUES(#{userId}, ${friendId})")
  int buildFriendShip(@Param("userId") long userId, @Param("friendId") long friendId);

  @Delete("DELETE FROM `friend` WHERE `userId`=#{userId} AND `friendId`=#{friendId}")
  int deleteFriendShip(@Param("userId") long userId, @Param("friendId") long friendId);

}
