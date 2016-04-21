package com.example.design.service.impl;

import com.example.design.mapper.ArticleMapper;
import com.example.design.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户作品服务
 * Created by lxh on 4/14/16.
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;


    public List<Article> all() {
        return articleMapper.getAll();
    }
}
