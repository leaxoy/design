package com.example.design.mapper;

import com.example.design.model.Comment;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论持久化接口.
 *
 * @author lxh
 * @version 0.1
 */
@Mapper
@Repository
public interface CommentMapper {

  @Select("SELECT * FROM `comment`")
  List<Comment> all();

  @Select("SELECT * FROM `comment` WHERE `userId`=#{userId}")
  List<Comment> byUserId(long userId);

  @Select("SELECT * FROM `comment` WHERE `commentId`=#{id}")
  Comment id(long id);

  @Insert("INSERT INTO `comment`(`userId`, `commentDate`, `comment`, `commentType`, "
          + "`commentForId`, `state`) VALUES(#{userId}, #{commentDate}, #{comment}, "
          + "#{commentType}, #{commentForId}, #{state})")
  int add(Comment comment);

  @Update("UPDATE `comment` set `comment`=#{comment} WHERE `commentId`=#{commentId}")
  int update(Comment comment);

  @Delete("")
  int delete(Comment comment);

}
