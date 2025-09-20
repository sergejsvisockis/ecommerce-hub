package io.github.sergejsvisockis.ecommerce.hub.store.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class CartBuyer {

    @Id
    @Column(name = "buyer_id")
    private UUID buyerId;

    private String firstName;

    private String lastName;

    public CartBuyer() {
        this.buyerId = UUID.randomUUID();
    }

    public UUID getBuyerId() {
        return buyerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
