package io.github.sergejsvisockis.ecommerce.hub.common.kafka;

import io.github.sergejsvisockis.ecommerce.hub.common.EComEventConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collection;
import java.util.List;

public class EComKafkaEventConsumer implements EComEventConsumer<List<String>, List<byte[]>> {

    private final KafkaConsumer<String, byte[]> consumer;

    public EComKafkaEventConsumer(KafkaConsumer<String, byte[]> consumer) {
        this.consumer = consumer;
    }

    @Override
    public List<byte[]> consumer(List<String> topics) {
        try {
            consumer.subscribe(topics);

            ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(100));

            return topics.stream()
                    .map(topic -> records.records(new TopicPartition(topic, 0)))
                    .flatMap(Collection::stream)
                    .map(ConsumerRecord::value)
                    .toList();

        } finally {
            consumer.commitAsync();
        }
    }

}
