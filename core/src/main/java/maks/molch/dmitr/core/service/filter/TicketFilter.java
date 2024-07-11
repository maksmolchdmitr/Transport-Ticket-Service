package maks.molch.dmitr.core.service.filter;

import java.sql.Timestamp;
import java.util.Optional;

public record TicketFilter(
        Optional<Timestamp> dateAndTime,
        Optional<String> departure,
        Optional<String> arrival,
        Optional<String> carrierName
) {
}
