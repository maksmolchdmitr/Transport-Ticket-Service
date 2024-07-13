package maks.molch.dmitr.core.repo;

import java.time.LocalDateTime;

public record TicketPrimaryKey(
        Integer routeId,
        LocalDateTime dateAndTime,
        Integer seatNumber
) {
}
