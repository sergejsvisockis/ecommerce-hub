package io.github.sergejsvisockis.ecommerce.hub.common.order.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Pattern;

@JsonDeserialize(builder = DebitCard.Builder.class)
public final class DebitCard {

    @Pattern(regexp = "^[0-9]{13,19}$",
            message = "Card number must be between 13 and 19 digits")
    private final String cardNumber;
    private final String cardHolderName;
    private final String expiryDate;

    private DebitCard(Builder builder) {
        this.cardNumber = builder.cardNumber;
        this.cardHolderName = builder.cardHolderName;
        this.expiryDate = builder.expiryDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String cardNumber;
        private String cardHolderName;
        private String expiryDate;

        private Builder() {
        }

        public Builder withCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder withCardHolderName(String cardHolderName) {
            this.cardHolderName = cardHolderName;
            return this;
        }

        public Builder withExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public DebitCard build() {
            return new DebitCard(this);
        }
    }
}
