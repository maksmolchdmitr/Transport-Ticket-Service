package maks.molch.dmitr.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketUpdateRequestDto(
        @NotNull
        int id,
        @NotNull
        @JsonProperty("route_id")
        int routeId,
        @NotNull
        @JsonProperty("date_and_time")
        LocalDateTime dateAndTime,
        @NotNull
        @JsonProperty("seat_number")
        int seatNumber,
        @NotNull
        @JsonProperty("price")
        BigDecimal price
) {
}
