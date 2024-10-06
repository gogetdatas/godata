package com.gogetdata.verification.infrastructure.config;

import com.gogetdata.verification.application.dto.TransmissionData;
import com.google.common.collect.ImmutableMap;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Bean
    public ProducerFactory<Long, TransmissionData> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs(), new LongSerializer(), new JsonSerializer<>());
    }

    @Bean
    public KafkaTemplate<Long, TransmissionData> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        return ImmutableMap.<String, Object>builder()
                .put("bootstrap.servers", "localhost:9092")
                .put("key.serializer", LongSerializer.class)
                .put("value.serializer", JsonSerializer.class)
                .build();
    }
}
