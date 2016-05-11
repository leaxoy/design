package com.example.design.mapper;

import com.example.design.model.Message;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 留言持久化接口.
 *
 * @author lxh
 * @version 0.1
 */
@Repository("MessageMapper")
@Mapper
public interface MessageMapper {
  @Insert("INSERT INTO `message`(`messageUserId`, `messageDate`, `message`,"
          + " `userId`, `shareId`, `showId`) VALUES (#{messageUserId}, "
          + "#{messageDate}, #{message}, #{userId}, #{shareId}, #{showId})")
  int add(Message message);            //添加留言

  @Update("UPDATE `message` SET `state` = 1 WHERE `messageId`=#{messageId}")
  int markDeleted(@Param("messageId") long messageId);             //根据ID标记删除留言

  @Update("UPDATE `message` SET `state` = 1 WHERE `shareId`=#{shareId}")
  int markDeletedByShareId(@Param("shareId") long shareId);             //根据shareID删除留言

  @Update("UPDATE `message` SET `state` = 1 WHERE `showId`=#{showId}")
  int markDeletedByShowId(@Param("showId") long showId);             //根据showID删除留言

  @Select("SELECT * FROM `message` WHERE `messageUserId`=#{messageUserId} && `state`=0")
  List<Message> findByMessageUserId(@Param("messageUserId") long messageUserId);//查询用户所发的所有留言

  @Select("SELECT * FROM `message` WHERE `userId`=#{userId} && `state`=0")
  List<Message> findByUserId(@Param("userId") long userId);    //查询用户所收的所有留言

  @Select("SELECT * FROM `message` WHERE `shareId`=#{shareId} && `state`=0")
  List<Message> findByShareId(@Param("shareId") long shareId);    //查询用户分享下的所有留言

  @Select("SELECT * FROM `message` WHERE `showId`=#{showId} && `state`=0")
  List<Message> findByShowId(@Param("showId") long showId);    //查询用户作品下的所有留言

  @Select(value = "SELECT * FROM `message` WHERE `messageId`=#{messageId})")
  Message findByMessageId(@Param("messageId") long messageId);     //查询用户留言所有详细信息
}
