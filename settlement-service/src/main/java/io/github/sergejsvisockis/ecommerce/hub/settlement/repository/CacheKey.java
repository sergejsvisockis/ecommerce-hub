package io.github.sergejsvisockis.ecommerce.hub.settlement.repository;

import java.util.Objects;

final class CacheKey {

    private final String email;
    private final String phone;

    public CacheKey(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CacheKey cacheKey = (CacheKey) obj;
        return Objects.equals(email, cacheKey.email)
                && Objects.equals(phone, cacheKey.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phone);
    }

}
