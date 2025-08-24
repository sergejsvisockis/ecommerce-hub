package io.github.sergejsvisockis.ecommerce.hub.common.kafka;

import io.github.sergejsvisockis.ecommerce.hub.common.EComEvent;
import io.github.sergejsvisockis.ecommerce.hub.common.EComEventProducer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.Future;

public class EComKafkaEventProducer implements EComEventProducer<EComEvent, Future<RecordMetadata>> {

    private final KafkaProducer<String, byte[]> producer;

    public EComKafkaEventProducer(KafkaProducer<String, byte[]> producer) {
        this.producer = producer;
    }

    @Override
    public Future<RecordMetadata> sendMessage(EComEvent event) {
        return producer.send(new ProducerRecord<>(
                event.getEventType().name(),
                event.getEventKey(),
                event.getData())
        );
    }
}
