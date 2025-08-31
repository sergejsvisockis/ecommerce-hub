package io.github.sergejsvisockis.ecommerce.hub.settlement.repository;

import io.github.sergejsvisockis.ecommerce.hub.settlement.entity.SettlementPayerDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheConfig {

    @Bean
    public RedisTemplate<CacheKey, SettlementPayerDetails> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<CacheKey, SettlementPayerDetails> template = new RedisTemplate<>();

        template.setConnectionFactory(connectionFactory);

        template.setKeySerializer(new RedisJsonSerializer());
        template.setValueSerializer(new RedisJsonSerializer());

        return template;
    }

}
