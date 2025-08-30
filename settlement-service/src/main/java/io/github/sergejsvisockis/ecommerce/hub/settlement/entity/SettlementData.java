package io.github.sergejsvisockis.ecommerce.hub.settlement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "settlement_data")
public class SettlementData {

    @Id
    @Column(name = "id", nullable = false, length = 36)
    private UUID id;

    @Column(name = "order_id", length = 36)
    private UUID orderId;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "currency", length = 3)
    private String currency;

    @Column(name = "settlement_payer_details_id")
    private UUID pyerId;

    @Transient
    private SettlementPayerDetails payerDetails;

    public SettlementData() {
    }

    private SettlementData(Builder builder) {
        this.id = builder.id != null ? builder.id : UUID.randomUUID();
        this.orderId = builder.orderId;
        this.orderDate = builder.orderDate;
        this.price = builder.price;
        this.currency = builder.currency;
        this.payerDetails = builder.payerDetails;
        this.pyerId = builder.pyerId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public UUID getPyerId() {
        return pyerId;
    }

    public void setPyerId(UUID pyerId) {
        this.pyerId = pyerId;
    }

    public SettlementPayerDetails getPayerDetails() {
        return payerDetails;
    }

    public static class Builder {
        private UUID id;
        private UUID orderId;
        private LocalDateTime orderDate;
        private BigDecimal price;
        private String currency;
        private UUID pyerId;
        private SettlementPayerDetails payerDetails;

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withOrderId(UUID orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder withOrderDate(LocalDateTime orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder withPyerId(UUID pyerId) {
            this.pyerId = pyerId;
            return this;
        }

        public Builder withPayerDetails(SettlementPayerDetails payerDetails) {
            this.payerDetails = payerDetails;
            return this;
        }

        public SettlementData build() {
            return new SettlementData(this);
        }

    }

}
