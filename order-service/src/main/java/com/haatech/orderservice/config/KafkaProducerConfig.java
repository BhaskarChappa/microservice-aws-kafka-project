package com.haatech.orderservice.config;

import com.haatech.orderservice.event.OrderCreatedEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${KAFKA_BOOTSTRAP_SERVERS:localhost:9092}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, OrderCreatedEvent> producerFactory() {

        Map<String, Object> properties = new HashMap<>();

        properties.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers
        );

        properties.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class
        );

        properties.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JacksonJsonSerializer.class
        );

        // Do not send Java package/class information in Kafka headers
        properties.put(
                "spring.json.add.type.headers",
                false
        );

        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate(
            ProducerFactory<String, OrderCreatedEvent> producerFactory) {

        return new KafkaTemplate<>(producerFactory);
    }
}