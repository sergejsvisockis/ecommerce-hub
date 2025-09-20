package io.github.sergejsvisockis.ecommerce.hub.store.domain.repository;

import io.github.sergejsvisockis.ecommerce.hub.store.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
}
