package maks.molch.dmitr.core.service.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public record TicketPurchase(
        int id,
        FullTicket ticket,
        String userLogin,
        LocalDateTime purchaseDatetime
) implements Serializable {
}
