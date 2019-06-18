package com.wedul.wedul_timeline.core.config.repository;

import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis 설정 (jedis)
 *
 * @author wedul
 * @since 2019-05-26
 **/
@RequiredArgsConstructor
@Configuration
@EnableRedisRepositories
@EnableCaching
public class RedisRepositoryConfig {

  private final RedisProperties redisProperties;
  public static final String TIME_LINE_ITEM = "TimeLineItem";

  private final RedissonClient redissonClient;

  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    return new JedisConnectionFactory(new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort()));
  }

  @Bean
  public RedisTemplate redisTemplate() {
    RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory());
    return redisTemplate;
  }

  @Bean
  CacheManager cacheManager()  {
    Map<String, CacheConfig> config = new HashMap<>();

    config.put(TIME_LINE_ITEM, new CacheConfig(
            TimeUnit.SECONDS.toMillis(3600),
            TimeUnit.SECONDS.toMillis(3600)));

    return new RedissonSpringCacheManager(redissonClient, config);
  }


}
