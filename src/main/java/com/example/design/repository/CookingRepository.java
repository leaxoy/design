package com.example.design.repository;

import com.example.design.model.Cooking;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lxh on 16/4/29.
 */
@Repository
public interface CookingRepository extends ElasticsearchRepository<Cooking, Long> {
}
