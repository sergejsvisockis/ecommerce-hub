package io.github.sergejsvisockis.ecommerce.hub.order;

import io.github.sergejsvisockis.ecommerce.hub.common.EComEvent;
import io.github.sergejsvisockis.ecommerce.hub.common.EComEventProducer;
import io.github.sergejsvisockis.ecommerce.hub.common.EventType;
import io.github.sergejsvisockis.ecommerce.hub.common.JsonUtil;
import io.github.sergejsvisockis.ecommerce.hub.common.order.dto.OrderRequest;
import io.github.sergejsvisockis.ecommerce.hub.common.order.dto.OrderResponse;
import io.github.sergejsvisockis.ecommerce.hub.common.order.dto.OrderState;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private final EComEventProducer<EComEvent, Future<RecordMetadata>> eventProducer;

    public OrderController(EComEventProducer<EComEvent, Future<RecordMetadata>> eventProducer) {
        this.eventProducer = eventProducer;
    }

    @PostMapping("/api/v1/orders")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {

        EComEvent event = new EComEvent.Builder()
                .withEventType(EventType.ORDER_CREATED)
                .withEventKey(request.getOrderId().toString())
                .withEventDate(request.getOrderDate())
                .withData(JsonUtil.toBytes(request))
                .build();

        Future<RecordMetadata> recordMetadataFuture = eventProducer.sendMessage(event);
        try {
            RecordMetadata recordMetadata = recordMetadataFuture.get();
            int partition = recordMetadata.partition();
            long offset = recordMetadata.offset();
            long timestamp = recordMetadata.timestamp();
            String topic = recordMetadata.topic();

            LOGGER.info("Message sent to topic: \"{}\" partition: \"{}\" with offset: \"{}\" at timestamp: \"{}\"",
                    topic, partition, offset, timestamp);

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(new OrderResponse(request.getOrderId(), OrderState.SUCCESS));
    }
}
