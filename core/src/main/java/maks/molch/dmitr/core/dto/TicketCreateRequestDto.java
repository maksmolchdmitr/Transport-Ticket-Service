package maks.molch.dmitr.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketCreateRequestDto(
        @JsonProperty("route_id")
        int routeId,
        @JsonProperty("date_and_time")
        LocalDateTime dateAndTime,
        @JsonProperty("seat_number")
        int seatNumber,
        @JsonProperty("price")
        BigDecimal price
) {
}
