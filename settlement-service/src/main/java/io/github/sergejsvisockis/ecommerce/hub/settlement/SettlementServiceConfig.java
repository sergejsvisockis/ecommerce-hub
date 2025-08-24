package io.github.sergejsvisockis.ecommerce.hub.settlement;

import io.github.sergejsvisockis.ecommerce.hub.common.EComEventConsumer;
import io.github.sergejsvisockis.ecommerce.hub.common.kafka.EComKafkaEventConsumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Properties;

@Configuration
public class SettlementServiceConfig {

    private final String bootstrapServer;
    private final String groupId;
    private final String keyDeserializer;
    private final String valueDeserializer;

    public SettlementServiceConfig(@Value("${kafka.bootstrap-server}") String bootstrapServer,
                                   @Value("${kafka.consumer.group-id}") String groupId,
                                   @Value("${kafka.consumer.key-deserializer}") String keyDeserializer,
                                   @Value("${kafka.consumer.value-deserializer}") String valueDeserializer) {
        this.bootstrapServer = bootstrapServer;
        this.groupId = groupId;
        this.keyDeserializer = keyDeserializer;
        this.valueDeserializer = valueDeserializer;
    }

    @Bean
    public SettlementConsumer settlementConsumer() {
        SettlementConsumer settlementConsumer = new SettlementConsumer(eventConsumer());
        settlementConsumer.setDaemon(true);
        settlementConsumer.start();
        return settlementConsumer;
    }

    @Bean
    public EComEventConsumer<List<String>, List<byte[]>> eventConsumer() {
        return new EComKafkaEventConsumer(kafkaConsumer());
    }

    @Bean
    public KafkaConsumer<String, byte[]> kafkaConsumer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServer);
        props.put("group.id", groupId);
        props.put("key.deserializer", keyDeserializer);
        props.put("value.deserializer", valueDeserializer);
        return new KafkaConsumer<>(props);
    }
}
