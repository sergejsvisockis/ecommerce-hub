package io.github.sergejsvisockis.ecommerce.hub.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class JsonUtil {

    private JsonUtil() {
    }

    private static ObjectMapper mapper;

    public static String toJson(Object object) {
        try {
            return getMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize object to JSON", e);
        }
    }

    public static byte[] toBytes(Object obj) {
        try {
            return getMapper().writeValueAsBytes(obj);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize object to JSON", e);
        }
    }

    public static <T> T fromBytes(byte[] bytes, Class<T> clazz) {
        try {
            return getMapper().readValue(bytes, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize JSON to object", e);
        }
    }

    public static String fromBytes(byte[] bytes) {
        try {
            return getMapper().readValue(bytes, String.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize JSON to string", e);
        }
    }

    private static ObjectMapper getMapper() {
        if (mapper == null) {
            return new ObjectMapper()
                    .registerModule(new JavaTimeModule());
        }
        return mapper;
    }
}
