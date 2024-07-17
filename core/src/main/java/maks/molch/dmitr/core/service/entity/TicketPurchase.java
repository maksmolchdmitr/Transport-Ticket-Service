package maks.molch.dmitr.core.service.entity;

import java.time.LocalDateTime;

public record TicketPurchase(
        int id,
        FullTicket ticket,
        String userLogin,
        LocalDateTime purchaseDatetime
) {
}
