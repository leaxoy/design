package com.example.design.repository;

import com.example.design.model.Cooking;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * cooking elastic repository.
 *
 * @author lxh
 * @version 0.1
 */
@Repository
public interface CookingRepository extends ElasticsearchRepository<Cooking, Long> {
}
