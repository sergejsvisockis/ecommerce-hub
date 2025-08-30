package io.github.sergejsvisockis.ecommerce.hub.settlement.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "settlement_payer_details")
public class SettlementPayerDetails {

    @Id
    @Column(name = "id", nullable = false, length = 36)
    private UUID id;

    @Column(name = "payer_first_name", length = 45)
    private String payerFirstName;

    @Column(name = "payer_last_name", length = 45)
    private String payerLastName;

    @Column(name = "payer_email", length = 90)
    private String payerEmail;

    @Column(name = "payer_phone", length = 15)
    private String payerPhone;

    @Column(name = "payer_address", length = 65)
    private String payerAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "debit_card_details_id",
            referencedColumnName = "id")
    private SettlementDebitCardDetails debitCardDetails;

    public SettlementPayerDetails() {
    }

    private SettlementPayerDetails(Builder builder) {
        this.id = builder.id != null ? builder.id : UUID.randomUUID();
        this.payerFirstName = builder.payerFirstName;
        this.payerLastName = builder.payerLastName;
        this.payerEmail = builder.payerEmail;
        this.payerPhone = builder.payerPhone;
        this.payerAddress = builder.payerAddress;
        this.debitCardDetails = builder.debitCardDetails;
    }

    public UUID getId() {
        return id;
    }

    public String getPayerFirstName() {
        return payerFirstName;
    }

    public String getPayerLastName() {
        return payerLastName;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public String getPayerPhone() {
        return payerPhone;
    }

    public String getPayerAddress() {
        return payerAddress;
    }

    public SettlementDebitCardDetails getDebitCardDetails() {
        return debitCardDetails;
    }

    public static class Builder {
        private UUID id;
        private String payerFirstName;
        private String payerLastName;
        private String payerEmail;
        private String payerPhone;
        private String payerAddress;
        private SettlementDebitCardDetails debitCardDetails;

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withPayerFirstName(String payerFirstName) {
            this.payerFirstName = payerFirstName;
            return this;
        }

        public Builder withPayerLastName(String payerLastName) {
            this.payerLastName = payerLastName;
            return this;
        }

        public Builder withPayerEmail(String payerEmail) {
            this.payerEmail = payerEmail;
            return this;
        }

        public Builder withPayerPhone(String payerPhone) {
            this.payerPhone = payerPhone;
            return this;
        }

        public Builder withPayerAddress(String payerAddress) {
            this.payerAddress = payerAddress;
            return this;
        }

        public Builder withDebitCardDetails(SettlementDebitCardDetails debitCardDetails) {
            this.debitCardDetails = debitCardDetails;
            return this;
        }

        public SettlementPayerDetails build() {
            return new SettlementPayerDetails(this);
        }

    }

}
