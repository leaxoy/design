package com.example.design.configuration;

import org.elasticsearch.node.NodeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * Created by lxh on 4/17/16.
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.design.repository")
public class ElasticSearch {
    @Bean
    public ElasticsearchOperations elasticsearchOperations() {
        return new ElasticsearchTemplate(NodeBuilder.nodeBuilder().local(true).node().client());
    }
}
