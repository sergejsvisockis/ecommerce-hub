package io.github.sergejsvisockis.ecommerce.hub.settlement.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import static io.github.sergejsvisockis.ecommerce.hub.settlement.util.JsonUtil.fromBytes;
import static io.github.sergejsvisockis.ecommerce.hub.settlement.util.JsonUtil.toBytes;

@Component
public class GenericRedisTemplate<K, V> {

    private final RedisTemplate<byte[], byte[]> redisTemplate;

    public GenericRedisTemplate(RedisTemplate<byte[], byte[]> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public V get(K key, Class<V> valueType) {
        byte[] bytes = redisTemplate.opsForValue().get(toBytes(key));
        return bytes != null
                ? fromBytes(bytes, valueType)
                : null;
    }

    public void put(K key, V value) {
        redisTemplate.opsForValue().set(
                toBytes(key),
                toBytes(value)
        );
    }
}
