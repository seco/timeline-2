package com.wedul.wedul_timeline.core.config.repository;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-07-09
 **/
@EnableCaching(proxyTargetClass = true)
@Configuration
@ConfigurationProperties(prefix = "redis")
@Data
public class CacheConfig extends CachingConfigurerSupport {

    private String host;
    private Integer port;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        // 현 버전에서 factory.setHostName() 등의 api는 Deprecated되었다.
        return new JedisConnectionFactory(new RedisStandaloneConfiguration(host, port));
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(jedisConnectionFactory());

        // 값은 json 문자열로 넣는다. @class 필드로 클래스 정보가 들어간다.
        RedisCacheConfiguration defaultConfig =
                RedisCacheConfiguration.defaultCacheConfig()
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                        .entryTtl(Duration.ofSeconds(20L));

        builder.cacheDefaults(defaultConfig);

        return builder.build();
    }

}
