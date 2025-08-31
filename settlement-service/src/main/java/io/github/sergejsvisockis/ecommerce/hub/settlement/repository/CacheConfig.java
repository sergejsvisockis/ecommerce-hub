package io.github.sergejsvisockis.ecommerce.hub.settlement.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class CacheConfig {

    @Bean
    public RedisTemplate<byte[], byte[]> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();

        template.setKeySerializer(new ByteArrayRedisSerializer());
        template.setValueSerializer(new ByteArrayRedisSerializer());
        template.setConnectionFactory(connectionFactory);

        return template;
    }

    static class ByteArrayRedisSerializer implements RedisSerializer<byte[]> {

        @Override
        public byte[] serialize(byte[] bytes) {
            return bytes;
        }

        @Override
        public byte[] deserialize(byte[] bytes) {
            return bytes;
        }
    }

}
