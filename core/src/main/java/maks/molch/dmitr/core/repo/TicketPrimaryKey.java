package maks.molch.dmitr.core.repo;

import java.sql.Timestamp;

public record TicketPrimaryKey(
        Integer routeId,
        Timestamp dateAndTime,
        Integer seatNumber
) {
}
