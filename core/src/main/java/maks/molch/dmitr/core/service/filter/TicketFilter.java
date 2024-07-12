package maks.molch.dmitr.core.service.filter;

import java.sql.Timestamp;
import java.util.Optional;

public record TicketFilter(
        Optional<Timestamp> startDateAndTime,
        Optional<Timestamp> endDateAndTime,
        Optional<String> departure,
        Optional<String> arrival,
        Optional<String> carrierName
) {
}
