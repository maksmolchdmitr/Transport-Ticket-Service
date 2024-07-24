package maks.molch.dmitr.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketUpdateRequestDto(
        @NotNull
        int id,
        @NotNull
        @JsonProperty("route_id")
        int routeId,
        @Schema(name = "date and time", example = "24.07.2024 13:39")
        @NotNull
        @JsonProperty("date_and_time")
        LocalDateTime dateAndTime,
        @Schema(name = "seat number", example = "1")
        @NotNull
        @JsonProperty("seat_number")
        int seatNumber,
        @Schema(name = "price", example = "1989.20")
        @NotNull
        @JsonProperty("price")
        BigDecimal price
) {
}
