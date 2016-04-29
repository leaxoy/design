package com.example.design.mapper;

import com.example.design.model.Message;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 留言持久化接口. Created by lxh on 4/20/16.
 */
@Repository("MessageMapper")
@Mapper
public interface MessageMapper {
  @Insert("INSERT INTO `message`(`messageUserId`, `messageDate`, `message`," +
          " `userId`, `shareId`, `showId`) VALUES (#{messageUserId}, " +
          "#{messageDate}, #{message}, #{userId}, #{shareId}, #{showId})")
  int addMessage(Message message);            //添加留言

  @Update("UPDATE `message` SET `state` = 1 WHERE `messageId`=#{messageId}")
  int markDelete(long messageId);             //根据ID标记删除留言

  @Update("UPDATE `message` SET `state` = 1 WHERE `shareId`=#{shareId}")
  int markDeleteByshareId(long shareId);             //根据shareID删除留言

  @Update("UPDATE `message` SET `state` = 1 WHERE `showId`=#{showId}")
  int markDeleteByshowId(long showId);             //根据showID删除留言

  @Select("SELETE * FROM `message` WHERE `messageUserId`=#{messageUserId} && `state`=0")
  List<Message> selectBymessageUserId(long messageUserId);    //查询用户所发的所有留言

  @Select("SELETE * FROM `message` WHERE `userId`=#{userId} && `state`=0")
  List<Message> selectByuserId(long userId);    //查询用户所收的所有留言

  @Select("SELETE * FROM `message` WHERE `shareId`=#{shareId} && `state`=0")
  List<Message> selectByshareId(long shareId);    //查询用户分享下的所有留言

  @Select("SELETE * FROM `message` WHERE `shaowId`=#{shaowId} && `state`=0")
  List<Message> selectByshowId(long shaowId);    //查询用户作品下的所有留言

  @Select(value = "SELETE * FROM `message` WHERE `messageId`=#{messageId})")
  Message selectBymessageId(Long messageId);     //查询用户留言所有详细信息

  @Update("UPDATE `message` SET `state` = 1 WHERE `shareId`=#{shareId}")
  int markDeleteByShareId(long shareId);             //根据shareID删除留言

  @Update("UPDATE `message` SET `state` = 1 WHERE `showId`=#{showId}")
  int markDeleteByShowId(long showId);             //根据showID删除留言

  @Select("SELETE * FROM `message` WHERE `messageUserId`=#{messageUserId} && `state`=0")
  List<Message> selectByMessageUserId(long messageUserId);    //查询用户所发的所有留言

  @Select("SELETE * FROM `message` WHERE `userId`=#{userId} && `state`=0")
  List<Message> selectByUserId(long userId);    //查询用户所收的所有留言

  @Select("SELETE * FROM `message` WHERE `shareId`=#{shareId} && `state`=0")
  List<Message> selectByShareId(long shareId);    //查询用户分享下的所有留言

  @Select("SELETE * FROM `message` WHERE `shaowId`=#{shaowId} && `state`=0")
  List<Message> selectByShowId(long shaowId);    //查询用户作品下的所有留言

  @Select(value = "SELETE * FROM `message` WHERE `messageId`=#{messageId})")
  Message selectByMessageId(Long messageId);     //查询用户留言所有详细信息
}
