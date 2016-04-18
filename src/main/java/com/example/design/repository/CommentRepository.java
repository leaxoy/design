package com.example.design.repository;

import com.example.design.model.Comment;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import java.sql.Date;
import java.util.List;

/**
 * Created by lxh on 4/17/16.
 */
public interface CommentRepository extends ElasticsearchCrudRepository<Comment, Long> {
    @Query("{}")
    List<Comment> findByUserOrContent(String query);

    @Query("{}")
    List<Comment> findCommentsBetweenDate(Date begin, Date end);


}
