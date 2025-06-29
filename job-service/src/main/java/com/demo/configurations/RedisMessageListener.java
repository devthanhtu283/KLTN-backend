package com.demo.configurations;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class RedisMessageListener implements MessageListener {
    private final CacheManager cacheManager; // Trường để tiêm CacheManager
    private static final Logger log = LoggerFactory.getLogger(RedisMessageListener.class);

    // Constructor thủ công để tiêm CacheManager
    public RedisMessageListener(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
        if (cacheManager == null) {
            throw new IllegalStateException("CacheManager cannot be null");
        }
        log.info("RedisMessageListener initialized with CacheManager: {}", cacheManager);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String cacheName = new String(message.getBody());
        log.info("Received Redis cache eviction message for cache: {}", cacheName);
        Cache cache = cacheManager.getCache(cacheName);

        if (cache != null) {
            log.info("Cleared cache: {}", cacheName);
            cache.clear();
        } else {
            log.warn("No cache found for cache name: {}", cacheName);
        }
    }
}