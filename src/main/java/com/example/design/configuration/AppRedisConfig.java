package com.example.design.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis Config.
 *
 * @author lxh
 * @version 0.1
 */
@Configuration
public class AppRedisConfig {

  @Autowired
  private RedisProperties redisProperties;


  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
    jedisConnectionFactory.setHostName(redisProperties.getHost());
    jedisConnectionFactory.setDatabase(redisProperties.getDatabase());
    return new JedisConnectionFactory();
  }

  /**
   * 构建redis template.
   *
   * @return redisTemplate
   */
  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory());
    template.setKeySerializer(new StringRedisSerializer());
    return template;
  }
}
