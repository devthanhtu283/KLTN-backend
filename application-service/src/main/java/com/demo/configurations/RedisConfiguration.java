package com.demo.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(RedisConfiguration.class);

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        if (redisConnectionFactory == null) {
            logger.error("RedisConnectionFactory is null. CacheManager cannot be initialized.");
            throw new IllegalStateException("RedisConnectionFactory is not properly configured.");
        }

        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        try {
            return RedisCacheManager.builder(redisConnectionFactory)
                    .cacheDefaults(cacheConfig)
                    .build();
        } catch (Exception e) {
            logger.error("Failed to initialize RedisCacheManager: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to initialize RedisCacheManager", e);
        }
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        if (redisConnectionFactory == null) {
            logger.error("RedisConnectionFactory is null. RedisTemplate cannot be initialized.");
            throw new IllegalStateException("RedisConnectionFactory is not properly configured.");
        }

        try {
            RedisTemplate<String, Object> template = new RedisTemplate<>();
            template.setConnectionFactory(redisConnectionFactory);
            template.setKeySerializer(new StringRedisSerializer());
            template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
            template.setHashKeySerializer(new StringRedisSerializer());
            template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
            template.afterPropertiesSet(); // Khởi tạo template
            logger.info("Redis Connection Established Successfully.");
            return template;
        } catch (Exception e) {
            logger.error("Failed to initialize RedisTemplate: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to initialize RedisTemplate", e);
        }
    }
}