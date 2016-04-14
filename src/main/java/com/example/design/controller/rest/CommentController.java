package com.example.design.controller.rest;

import com.example.design.model.Comment;
import com.example.design.service.CommentService;
import com.example.design.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lxh on 4/14/16.
 */
@RestController
@RequestMapping("api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @RequestMapping()
    public ResponseEntity all() {
        List<Comment> comments = commentService.all();
        if (comments == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity id(@PathVariable int id) {
        Comment comment = commentService.id(id);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comment);
    }

    @RequestMapping("{id}/comment")
    public ResponseEntity comment(@PathVariable int id) {
        Comment comment = commentService.id(id);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comment.getComment());
    }

    @RequestMapping("{id}/commentdate")
    public ResponseEntity commentDate(@PathVariable int id) {
        Comment comment = commentService.id(id);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comment.getCommentDate());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody Comment comment) {
        return ResponseEntity.ok(comment);
    }
}
