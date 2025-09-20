package io.github.sergejsvisockis.ecommerce.hub.store.domain.repository;

import io.github.sergejsvisockis.ecommerce.hub.store.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}
