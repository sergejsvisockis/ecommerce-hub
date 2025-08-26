package io.github.sergejsvisockis.ecommerce.hub.settlement;

import io.github.sergejsvisockis.ecommerce.hub.common.EComEventConsumer;
import io.github.sergejsvisockis.ecommerce.hub.common.EventType;
import io.github.sergejsvisockis.ecommerce.hub.common.JsonUtil;
import io.github.sergejsvisockis.ecommerce.hub.common.order.dto.OrderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SettlementConsumer extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(SettlementConsumer.class);

    private final EComEventConsumer<List<String>, List<byte[]>> eventConsumer;

    public SettlementConsumer(EComEventConsumer<List<String>, List<byte[]>> eventConsumer) {
        this.eventConsumer = eventConsumer;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                List<byte[]> eComEvents = consumeSettlementRecord();
                if (!eComEvents.isEmpty()) {
                    eComEvents.forEach(event ->
                            {
                                OrderRequest request = JsonUtil.fromBytes(event, OrderRequest.class);
                                LOGGER.info("Consumed event: {}", JsonUtil.toJson(request));
                            }
                    );
                } else {
                    // If no events, sleep a bit to avoid CPU spinning
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                LOGGER.info("Settlement consumer thread interrupted, exiting");
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                LOGGER.error("Error while consuming settlement records", e);
                // Continue the loop even if there's an error
            }
        }
        LOGGER.info("Settlement consumer thread stopped");
    }

    private List<byte[]> consumeSettlementRecord() {
        return eventConsumer.consumer(List.of(EventType.ORDER_CREATED.name()));
    }

}
