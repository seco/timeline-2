package com.wedul.wedul_timeline.core.config.repository;

import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
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
  public static final String TIME_LINE_ITME = "TimeLineItem";

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

    config.put(TIME_LINE_ITME, new CacheConfig(
            TimeUnit.SECONDS.toMillis(60),
            TimeUnit.SECONDS.toMillis(30)));

    return new RedissonSpringCacheManager(Redisson.create(), config);
  }

}
