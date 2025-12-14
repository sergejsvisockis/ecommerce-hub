package io.github.sergejsvisockis.ecommerce.hub.settlement.mapper;

import io.github.sergejsvisockis.ecommerce.hub.common.order.dto.SettlementAggregate;
import io.github.sergejsvisockis.ecommerce.hub.settlement.entity.SettlementData;
import org.springframework.stereotype.Component;

@Component
public class SettlementDataMapper {

    public SettlementData mapToSettlementData(SettlementAggregate settlementAggregate) {
        return new SettlementData.Builder()
                .withId(settlementAggregate.getOrderId())
                .withOrderId(settlementAggregate.getOrderId())
                .withOrderDate(settlementAggregate.getOrderDate())
                .withPrice(settlementAggregate.getTotalPrice().amount())
                .withCurrency(settlementAggregate.getTotalPrice().currency().name())
                .build();
    }

}
