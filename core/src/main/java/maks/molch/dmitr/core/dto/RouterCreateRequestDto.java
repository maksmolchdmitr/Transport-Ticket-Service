package maks.molch.dmitr.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RouterCreateRequestDto(
        @JsonProperty("departure")
        String departure,
        @JsonProperty("arrival")
        String arrival,
        @JsonProperty("carrier_name")
        String carrierName,
        @JsonProperty("duration_in_minutes")
        int durationInMinutes,
        @JsonProperty("seat_count")
        int seatCount
) {
}
