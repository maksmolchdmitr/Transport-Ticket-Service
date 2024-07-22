package maks.molch.dmitr.analytic.service;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.analytic.service.entity.TicketPurchase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PurchasedTicketsKafkaConsumer {
    private final PurchasedTicketHandler purchasedTicketHandler;

    @KafkaListener(topics = "${kafka.topicName}")
    public void consume(TicketPurchase ticketPurchase) {
        purchasedTicketHandler.handle(ticketPurchase);
    }
}
