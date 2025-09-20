package io.github.sergejsvisockis.ecommerce.hub.settlement.consumer;

import io.github.sergejsvisockis.ecommerce.hub.common.JsonUtil;
import io.github.sergejsvisockis.ecommerce.hub.common.order.dto.OrderRequest;
import io.github.sergejsvisockis.ecommerce.hub.settlement.service.SettlementDataService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SettlementConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SettlementConsumer.class);

    private final SettlementDataService settlementDataService;

    public SettlementConsumer(SettlementDataService settlementDataService) {
        this.settlementDataService = settlementDataService;
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.consumer.order-created-topic}"
    )
    public void consumeSettlement(ConsumerRecord<String, byte[]> data) {
        OrderRequest orderRequest = JsonUtil.fromBytes(data.value(), OrderRequest.class);
        LOGGER.info("Consumed event: {}", JsonUtil.toJson(orderRequest));
        settlementDataService.saveSettlementData(orderRequest);
    }

}
