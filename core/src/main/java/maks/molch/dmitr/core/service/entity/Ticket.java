package maks.molch.dmitr.core.service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Ticket(
        int routeId,
        LocalDateTime dateAndTime,
        int seatNumber,
        BigDecimal price
) {
}
