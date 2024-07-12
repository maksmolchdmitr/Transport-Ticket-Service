package maks.molch.dmitr.core.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record FullTicket(
        FullRoute fullRoute,
        Timestamp dateAndTime,
        int seatNumber,
        BigDecimal price
) {
}
