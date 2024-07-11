package maks.molch.dmitr.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RouteDto(
        @JsonProperty("departure")
        String departure,
        @JsonProperty("arrival")
        String arrival,
        @JsonProperty("carrier")
        CarrierDto carrier,
        @JsonProperty("duration_in_minutes")
        int durationInMinutes,
        @JsonProperty("seat_count")
        int seatCount
) {
}
