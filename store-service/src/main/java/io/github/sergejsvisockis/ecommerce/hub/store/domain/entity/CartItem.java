package io.github.sergejsvisockis.ecommerce.hub.store.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class CartItem {

    @Id
    private UUID cartItemId;

    private UUID productId;

    private Integer quantity;

    public CartItem() {
        cartItemId = UUID.randomUUID();
    }

    public UUID getCartItemId() {
        return cartItemId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
