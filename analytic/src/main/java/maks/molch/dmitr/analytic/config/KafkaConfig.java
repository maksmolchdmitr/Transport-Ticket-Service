package maks.molch.dmitr.analytic.config;

import maks.molch.dmitr.analytic.entity.TicketPurchase;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @ConfigurationProperties(prefix = "kafka")
    public record KafkaProps(
            String serverUrl
    ) {
    }

    @Bean
    public KafkaTemplate<Long, TicketPurchase> kafkaTemplate(KafkaProps kafkaProps) {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProps.serverUrl());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        DefaultKafkaProducerFactory<Long, TicketPurchase> kafkaProducer = new DefaultKafkaProducerFactory<>(properties);
        return new KafkaTemplate<>(kafkaProducer);
    }
}
