package com.example.design.mapper;

import com.example.design.model.Friend;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lxh on 4/20/16.
 */
@Repository
public interface FriendMapper {

  @Select("SELECT * FROM `friend` WHERE `userId`=#{id}")
  List<Friend> getByUserId(long id);

  @Insert("INSERT INTO `friend` VALUES(#{userId}, ${friendId})")
  int buildFriendShip(long userId, long friendId);

  @Delete("DELETE FROM `friend` WHERE `userId`=#{userId} AND `friendId`=#{friendId}")
  int deleteFriendShip(long userId, long friendId);

}
