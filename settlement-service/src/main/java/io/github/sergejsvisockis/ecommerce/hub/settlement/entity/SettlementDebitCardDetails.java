package io.github.sergejsvisockis.ecommerce.hub.settlement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "settlement_debit_card_details")
public class SettlementDebitCardDetails {

    @Id
    @Column(name = "id", nullable = false, length = 36)
    private UUID id;

    @Column(name = "card_number", length = 16)
    private String cardNumber;

    @Column(name = "card_expiry", length = 5)
    private String cardExpiry;

    @Column(name = "card_holder_name", length = 150)
    private String cardHolderName;

    public SettlementDebitCardDetails() {
    }

    private SettlementDebitCardDetails(Builder builder) {
        this.id = builder.id != null ? builder.id : UUID.randomUUID();
        this.cardNumber = builder.cardNumber;
        this.cardExpiry = builder.cardExpiry;
        this.cardHolderName = builder.cardHolderName;
    }

    public UUID getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public static class Builder {

        private UUID id;
        private String cardNumber;
        private String cardExpiry;
        private String cardHolderName;

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder withCardExpiry(String cardExpiry) {
            this.cardExpiry = cardExpiry;
            return this;
        }

        public Builder withCardHolderName(String cardHolderName) {
            this.cardHolderName = cardHolderName;
            return this;
        }

        public SettlementDebitCardDetails build() {
            return new SettlementDebitCardDetails(this);
        }
    }
}
