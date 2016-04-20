package com.example.design.mapper;

import com.example.design.model.resource.Article;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 作品持久化接口.
 * Created by lxh on 4/14/16.
 */
@Repository("articleMapper")
public interface ArticleMapper {
    @Select("SELECT * FROM article")
    List<Article> getAll();

    @Select("SELECT * FROM article WHERE id = #{id}")
    Article getById(int id);

    @Update("UPDATE `article` SET WHERE ``=#{id}")
    int updateOne(Article article);
}
