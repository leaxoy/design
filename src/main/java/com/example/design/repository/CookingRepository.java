package com.example.design.repository;

import com.example.design.model.Cooking;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by lxh on 16/4/25.
 */
public interface CookingRepository extends ElasticsearchRepository<Cooking, Long> {
}
