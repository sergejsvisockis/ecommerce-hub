package io.github.sergejsvisockis.ecommerce.hub.settlement.repository;

import io.github.sergejsvisockis.ecommerce.hub.settlement.entity.SettlementData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SettlementDataRepository extends JpaRepository<SettlementData, UUID> {
}
