package maks.molch.dmitr.core.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record Ticket(
        int routeId,
        Timestamp dateAndTime,
        int seatNumber,
        BigDecimal price
) {
}
