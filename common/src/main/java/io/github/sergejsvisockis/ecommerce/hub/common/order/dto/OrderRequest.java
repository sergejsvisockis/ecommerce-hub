package io.github.sergejsvisockis.ecommerce.hub.common.order.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonDeserialize(builder = OrderRequest.Builder.class)
public final class OrderRequest {

    private final UUID orderId;
    private final LocalDateTime orderDate;
    private final Product product;
    private final Price totalPrice;
    private final OrderStatus status;
    private final Payer payer;

    private OrderRequest(Builder builder) {
        this.orderId = builder.orderId;
        this.orderDate = builder.orderDate;
        this.product = builder.product;
        this.totalPrice = builder.totalPrice;
        this.status = builder.status;
        this.payer = builder.payer;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public Product getProduct() {
        return product;
    }

    public Price getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Payer getPayer() {
        return payer;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID orderId;
        private LocalDateTime orderDate;
        private Product product;

        private Price totalPrice;
        private OrderStatus status;
        private Payer payer;

        private Builder() {
        }

        public Builder withOrderId(UUID orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder withOrderDate(LocalDateTime orderDate) {
            this.orderDate = orderDate;
            return this;
        }


        public Builder withProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder withTotalPrice(Price totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder withStatus(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Builder withPayer(Payer payer) {
            this.payer = payer;
            return this;
        }

        public OrderRequest build() {
            return new OrderRequest(this);
        }
    }
}
