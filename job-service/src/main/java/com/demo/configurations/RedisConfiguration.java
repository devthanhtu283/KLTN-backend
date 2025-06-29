package com.demo.configurations;

import com.demo.helpers.CacheName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Configuration
@EnableCaching
@ConfigurationProperties(prefix = "spring.data.redis")
@Setter
public class RedisConfiguration {

    private String host;
    private Integer port;
    private String password;

    // ✅ Đã bật default typing cho ObjectMapper
    private static GenericJackson2JsonRedisSerializer jsonSerializer() {
        ObjectMapper om = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();

        om.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );

        return new GenericJackson2JsonRedisSerializer(om);
    }

    /* ---------- Redis connection ---------- */

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration cfg = new RedisStandaloneConfiguration(
                Optional.ofNullable(host).orElse("localhost"),
                Optional.ofNullable(port).orElse(6379)
        );
        if (password != null && !password.isBlank()) {
            cfg.setPassword(RedisPassword.of(password));
        }
        return new LettuceConnectionFactory(cfg);
    }

    /* ---------- RedisTemplate ---------- */

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory cf) {
        StringRedisSerializer keySer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer valSer = jsonSerializer();

        RedisTemplate<String, Object> tpl = new RedisTemplate<>();
        tpl.setConnectionFactory(cf);
        tpl.setKeySerializer(keySer);
        tpl.setHashKeySerializer(keySer);
        tpl.setValueSerializer(valSer);
        tpl.setHashValueSerializer(valSer);
        tpl.afterPropertiesSet();
        return tpl;
    }

    /* ---------- RedisCacheManager ---------- */

    @Bean
    @Primary
    public RedisCacheManager redisCacheManager(RedisConnectionFactory cf) {
        StringRedisSerializer keySer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer valSer = jsonSerializer();

        RedisCacheConfiguration defaultCfg = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valSer))
                .disableCachingNullValues();

        return RedisCacheManager.builder(cf)
                .cacheDefaults(defaultCfg)
                .transactionAware()
                .withInitialCacheConfigurations(getCacheConfigurations(defaultCfg))
                .build();
    }

    private Map<String, RedisCacheConfiguration> getCacheConfigurations(RedisCacheConfiguration base) {
        Map<String, RedisCacheConfiguration> cfg = new HashMap<>();
        cfg.put(CacheName.JOBS.value(), base.entryTtl(Duration.ofMinutes(10)));
        return cfg;
    }

    /* ---------- Redis pub/sub ---------- */

    @Bean
    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory cf,
                                                        RedisMessageListener listener) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(cf);
        container.addMessageListener(listener, new ChannelTopic("userCacheEvict"));
        return container;
    }
}
