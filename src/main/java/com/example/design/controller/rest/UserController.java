package com.example.design.controller.rest;

import com.example.design.model.resource.Comment;
import com.example.design.model.resource.User;
import com.example.design.service.impl.CommentService;
import com.example.design.service.impl.MenuService;
import com.example.design.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lxh on 4/17/16.
 */
@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private MenuService menuService;

    @RequestMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {
        User user = userService.getByAccountName(String.valueOf(id));
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @RequestMapping("{id}/comment")
    public ResponseEntity getCommentsByUserId(@PathVariable int id) {
        List<Comment> comments = commentService.findCommentsByUserId(id);
        if (comments == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
