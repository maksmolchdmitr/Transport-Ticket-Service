package maks.molch.dmitr.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RouteUpdateRequestDto(
        @NotNull
        int id,
        @NotBlank
        @JsonProperty("departure")
        String departure,
        @NotBlank
        @JsonProperty("arrival")
        String arrival,
        @NotBlank
        @JsonProperty("carrier_name")
        String carrierName,
        @Positive
        @JsonProperty("duration_in_minutes")
        int durationInMinutes,
        @Positive
        @JsonProperty("seat_count")
        int seatCount
) {
}
