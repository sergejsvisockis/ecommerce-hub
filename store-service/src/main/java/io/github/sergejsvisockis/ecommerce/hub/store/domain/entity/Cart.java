package io.github.sergejsvisockis.ecommerce.hub.store.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Cart {

    @Id
    private UUID cartId;

    private LocalDateTime createdAt;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private List<CartItem> items = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "buyer_id",
            referencedColumnName = "buyer_id")
    private CartBuyer buyer;

    public Cart() {
        cartId = UUID.randomUUID();
    }

    public UUID getCartId() {
        return cartId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public CartBuyer getBuyer() {
        return buyer;
    }

    public void setBuyer(CartBuyer buyer) {
        this.buyer = buyer;
    }
}
