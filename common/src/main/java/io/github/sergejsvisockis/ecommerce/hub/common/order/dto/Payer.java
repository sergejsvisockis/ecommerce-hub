package io.github.sergejsvisockis.ecommerce.hub.common.order.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Pattern;

@JsonDeserialize(builder = Payer.Builder.class)
public final class Payer {

    private final String firstName;
    private final String lastName;
    private final String email;
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", 
            message = "Phone number must be in international " +
                    "format with 7-15 digits, optionally starting with +")
    private final String phone;
    private final String address;
    private final DebitCard debitCardDetails;

    private Payer(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
        this.debitCardDetails = builder.debitCardDetails;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public DebitCard getDebitCardDetails() {
        return debitCardDetails;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String address;
        private DebitCard debitCardDetails;

        private Builder() {
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withDebitCardDetails(DebitCard debitCardDetails) {
            this.debitCardDetails = debitCardDetails;
            return this;
        }

        public Payer build() {
            return new Payer(this);
        }
    }
}
