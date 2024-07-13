package maks.molch.dmitr.core.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Ticket(
        int routeId,
        LocalDateTime dateAndTime,
        int seatNumber,
        BigDecimal price
) {
}
