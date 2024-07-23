package maks.molch.dmitr.core.service.kafka;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.service.kafka.entity.TicketPurchase;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static maks.molch.dmitr.core.config.KafkaConfig.KafkaProperties;

@Service
@AllArgsConstructor
public class KafkaAnalyticProducer {
    private final KafkaProperties kafkaProperties;
    private final KafkaTemplate<Long, TicketPurchase> kafkaTemplate;

    public void sendTicketPurchase(TicketPurchase ticketPurchase) {
        kafkaTemplate.send(kafkaProperties.topicName(), ticketPurchase);
    }
}
