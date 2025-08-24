package io.github.sergejsvisockis.ecommerce.hub.order;

import io.github.sergejsvisockis.ecommerce.hub.common.EComEvent;
import io.github.sergejsvisockis.ecommerce.hub.common.EComEventProducer;
import io.github.sergejsvisockis.ecommerce.hub.common.kafka.EComKafkaEventProducer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;
import java.util.concurrent.Future;

@Configuration
public class OrderServiceConfig {

    private final String bootstrapServer;
    private final String keySerializer;
    private final String valueSerializer;

    public OrderServiceConfig(@Value("${kafka.bootstrap-server}") String bootstrapServer,
                              @Value("${kafka.consumer.key-serializer}") String keySerializer,
                              @Value("${kafka.consumer.value-serializer}") String valueSerializer) {
        this.bootstrapServer = bootstrapServer;
        this.keySerializer = keySerializer;
        this.valueSerializer = valueSerializer;
    }

    @Bean
    public EComEventProducer<EComEvent, Future<RecordMetadata>> eventProducer() {
        return new EComKafkaEventProducer(kafkaProducer());
    }

    @Bean
    public KafkaProducer<String, byte[]> kafkaProducer() {
        Properties props = new Properties();

        props.put("bootstrap.servers", bootstrapServer);
        props.put("key.serializer", keySerializer);
        props.put("value.serializer", valueSerializer);

        return new KafkaProducer<>(props);
    }

}
