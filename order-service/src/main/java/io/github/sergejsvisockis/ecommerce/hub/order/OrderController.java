package io.github.sergejsvisockis.ecommerce.hub.order;

import io.github.sergejsvisockis.ecommerce.hub.common.JsonUtil;
import io.github.sergejsvisockis.ecommerce.hub.common.order.dto.OrderRequest;
import io.github.sergejsvisockis.ecommerce.hub.common.order.dto.OrderResponse;
import io.github.sergejsvisockis.ecommerce.hub.common.order.dto.OrderState;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private final String orderCreatedTopic;
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public OrderController(@Value("${spring.kafka.producer.order-created-topic}") String orderCreatedTopic,
                           KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.orderCreatedTopic = orderCreatedTopic;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/api/v1/orders")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        try {
            CompletableFuture<SendResult<String, byte[]>> sendResultFuture =
                    kafkaTemplate.send(orderCreatedTopic, JsonUtil.toBytes(request));

            SendResult<String, byte[]> result = sendResultFuture.get();
            RecordMetadata metadata = result.getRecordMetadata();

            int partition = metadata.partition();
            long offset = metadata.offset();
            long timestamp = metadata.timestamp();
            String topic = metadata.topic();

            LOGGER.info("Message sent to topic: \"{}\" partition: \"{}\" with offset: \"{}\" at timestamp: \"{}\"",
                    topic, partition, offset, timestamp);

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(new OrderResponse(request.getOrderId(), OrderState.SUCCESS));
    }
}
