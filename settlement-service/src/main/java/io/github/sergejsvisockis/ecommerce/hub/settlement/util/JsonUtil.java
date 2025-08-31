package io.github.sergejsvisockis.ecommerce.hub.settlement.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtil {

    private JsonUtil() {
    }

    private static ObjectMapper mapper;

    public static <T> T fromBytes(byte[] bytes, Class<T> clazz) {
        try {
            if (bytes == null) {
                return null;
            }
            return getObjectMapper().readValue(bytes, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize JSON", e);
        }
    }

    public static byte[] toBytes(Object obj) {
        try {
            return getObjectMapper().writeValueAsBytes(obj);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize JSON", e);
        }
    }

    private static ObjectMapper getObjectMapper() {
        if (mapper == null) {
            return new ObjectMapper()
                    .setVisibility(
                            PropertyAccessor.FIELD,
                            Visibility.ANY
                    );
        }
        return mapper;
    }

}
