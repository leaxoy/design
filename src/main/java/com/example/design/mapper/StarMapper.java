package com.example.design.mapper;

import com.example.design.model.Star;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 关注持久化接口. Created by lxh on 4/20/16.
 */
@Repository
@Mapper
public interface StarMapper {

  @Select("SELECT * FROM `star`")
  List<Star> all();

  @Select("SELECT * FROM `star` WHERE `starId` = #{starId}")
  Star findById(long starId);

  @Select("SELECT * FROM `star` WHERE `userId` = #{userId}")
  List<Star> findByUserId(long userId);

  @Insert("INSERT INTO `star`() VALUES()")
  int addStar(Star star);

  @Delete("DELETE FROM `star` WHERE `starId` = #{starId}")
  int deleteStar(Star star);

  @Delete("DELETE FROM `star` WHERE `starId` = #{starId}")
  int deleteStarById(long starId);
}
