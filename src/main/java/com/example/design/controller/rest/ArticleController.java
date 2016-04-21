package com.example.design.controller.rest;

import com.example.design.authorization.annotation.Authorization;
import com.example.design.constant.UserRole;
import com.example.design.model.Article;
import com.example.design.service.impl.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 作品的rest api.
 * Created by lxh on 16/4/21.
 */
@RestController
@RequestMapping("api/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    @Authorization({UserRole.ADMIN, UserRole.USER})
    public ResponseEntity all() {
        return null;
    }

    @RequestMapping(method = RequestMethod.GET)
    @Authorization({UserRole.ADMIN, UserRole.USER})
    public ResponseEntity id(@PathVariable long id) {
        return ResponseEntity.ok(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @Authorization({UserRole.ADMIN, UserRole.USER})
    public ResponseEntity post(@RequestBody Article article) {
        return ResponseEntity.ok(article);
    }


}
