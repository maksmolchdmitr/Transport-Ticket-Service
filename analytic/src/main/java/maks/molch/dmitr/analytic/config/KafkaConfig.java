package maks.molch.dmitr.analytic.config;

import maks.molch.dmitr.analytic.service.entity.TicketPurchase;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @ConfigurationProperties(prefix = "kafka")
    public record KafkaProps(
            String serverUrl,
            String topicName,
            String groupId
    ) {
    }

    @Bean
    public KafkaListenerContainerFactory<?> kafkaListenerContainerFactory(KafkaProps kafkaProps) {
        ConcurrentKafkaListenerContainerFactory<Long, TicketPurchase> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(kafkaProps));
        return factory;
    }

    public ConsumerFactory<Long, TicketPurchase> consumerFactory(KafkaProps kafkaProps) {
        Map<String, Object> properties = new HashMap<>();

        JsonDeserializer<TicketPurchase> deserializer = new JsonDeserializer<>(TicketPurchase.class);
        deserializer.addTrustedPackages("*");
        deserializer.setRemoveTypeHeaders(false);
        deserializer.setUseTypeMapperForKey(true);

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProps.serverUrl());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProps.groupId());
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(properties, new LongDeserializer(), deserializer);
    }
}
