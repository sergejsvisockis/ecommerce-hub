package io.github.sergejsvisockis.ecommerce.hub.settlement.mapper;

import io.github.sergejsvisockis.ecommerce.hub.common.order.dto.OrderRequest;
import io.github.sergejsvisockis.ecommerce.hub.settlement.entity.SettlementData;
import org.springframework.stereotype.Component;

@Component
public class SettlementDataMapper {

    public SettlementData mapToSettlementData(OrderRequest orderRequest) {
        return new SettlementData.Builder()
                .withId(orderRequest.getOrderId())
                .withOrderId(orderRequest.getOrderId())
                .withOrderDate(orderRequest.getOrderDate())
                .withPrice(orderRequest.getTotalPrice().amount())
                .withCurrency(orderRequest.getTotalPrice().currency().name())
                .build();
    }

}
