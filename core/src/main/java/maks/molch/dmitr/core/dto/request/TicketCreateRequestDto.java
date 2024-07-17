package maks.molch.dmitr.core.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketCreateRequestDto(
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
