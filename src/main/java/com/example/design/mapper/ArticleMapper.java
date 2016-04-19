package com.example.design.mapper;

import com.example.design.model.Article;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lxh on 4/14/16.
 */
@CacheNamespace(size = 100)
@Component
public interface ArticleMapper {
    @Select("SELECT * FROM article")
    List<Article> getAll();

    @Select("SELECT * FROM article WHERE id = #{id}")
    List<Article> getById();

    @Update("UPDATE ")
    void updateOne(Article article);
}
