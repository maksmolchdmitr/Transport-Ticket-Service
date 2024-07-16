package maks.molch.dmitr.core.service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FullTicket(
        FullRoute fullRoute,
        LocalDateTime dateAndTime,
        int seatNumber,
        BigDecimal price
) {
}