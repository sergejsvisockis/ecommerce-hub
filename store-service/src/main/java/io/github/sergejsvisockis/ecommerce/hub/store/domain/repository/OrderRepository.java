package io.github.sergejsvisockis.ecommerce.hub.store.domain.repository;

import io.github.sergejsvisockis.ecommerce.hub.store.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
