package com.example.design.mapper;

import com.example.design.model.Show;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 作品持久化接口. Created by lxh on 4/14/16.
 */
@Repository("articleMapper")
public interface ShowMapper {
  @Select("SELECT * FROM show")
  List<Show> getAll();

  @Select("SELECT * FROM show WHERE id = #{id}")
  Show getById(int id);

  @Update("UPDATE `show` SET WHERE ``=#{id}")
  int updateOne(Show show);

  @Delete("DELETE FROM `show` WHERE `id`=#{id}")
  int deleteId(long id);
}
