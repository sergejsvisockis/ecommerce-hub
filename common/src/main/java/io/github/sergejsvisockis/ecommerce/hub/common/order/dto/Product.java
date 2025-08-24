package io.github.sergejsvisockis.ecommerce.hub.common.order.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.UUID;

@JsonDeserialize(builder = Product.Builder.class)
public final class Product {

    private final UUID productId;
    private final Price price;
    private final Integer quantity;

    private Product(Builder builder) {
        this.productId = builder.productId;
        this.price = builder.price;
        this.quantity = builder.quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public Price getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID productId;
        private Price price;
        private Integer quantity;

        private Builder() {
        }

        public Builder withProductId(UUID productId) {
            this.productId = productId;
            return this;
        }

        public Builder withPrice(Price price) {
            this.price = price;
            return this;
        }

        public Builder withQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
