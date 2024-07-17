package maks.molch.dmitr.core.service.filter;

import java.time.LocalDateTime;
import java.util.Optional;

public record TicketFilter(
        Optional<LocalDateTime> startDateAndTime,
        Optional<LocalDateTime> endDateAndTime,
        Optional<String> departure,
        Optional<String> arrival,
        Optional<String> carrierName,
        boolean withoutPurchased
) {
}
