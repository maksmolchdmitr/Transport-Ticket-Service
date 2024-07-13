package maks.molch.dmitr.core.repo;

import java.time.LocalDateTime;

public record TicketUniqueId(
        Integer routeId,
        LocalDateTime dateAndTime,
        Integer seatNumber
) {
}
