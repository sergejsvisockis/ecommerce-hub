package io.github.sergejsvisockis.ecommerce.hub.settlement.repository;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.IOException;

public class RedisJsonSerializer implements RedisSerializer<Object> {

    private ObjectMapper objectMapper;

    @Override
    public byte[] serialize(Object value) throws SerializationException {
        try {
            return getObjectMapper().writeValueAsBytes(value);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to serialise object", e);
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        try {
            if (bytes == null) {
                return null;
            }
            return getObjectMapper().readValue(bytes, Object.class);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to de-serialise object", e);
        }
    }

    private ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            return new ObjectMapper()
                    .setVisibility(
                            PropertyAccessor.FIELD,
                            Visibility.ANY
                    );
        }
        return objectMapper;
    }

}
