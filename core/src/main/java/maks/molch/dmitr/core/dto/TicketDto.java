package maks.molch.dmitr.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record TicketDto(
        @JsonProperty("route")
        RouteDto route,
        @JsonProperty("date_and_time")
        Timestamp dateAndTime,
        @JsonProperty("seat_number")
        int seatNumber,
        @JsonProperty("price")
        BigDecimal price
) {
}
