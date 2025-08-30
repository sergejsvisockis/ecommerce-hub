package io.github.sergejsvisockis.ecommerce.hub.settlement.repository;

import io.github.sergejsvisockis.ecommerce.hub.settlement.entity.SettlementPayerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SettlementPayerRepository extends JpaRepository<SettlementPayerDetails, UUID> {

    @Query("SELECT p FROM SettlementPayerDetails p " +
            "WHERE p.payerEmail = :email " +
            "AND p.payerPhone = :phone")
    Optional<SettlementPayerDetails> findBySettlementPayerEmail(@Param("email") String email,
                                                                @Param("phone") String phone);

}
