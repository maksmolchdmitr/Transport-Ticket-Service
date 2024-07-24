package maks.molch.dmitr.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RouteUpdateRequestDto(
        @NotNull
        int id,
        @Schema(name = "departure", example = "Nizhny Novgorod")
        @NotBlank
        @JsonProperty("departure")
        String departure,
        @Schema(name = "arrival", example = "Moscow")
        @NotBlank
        @JsonProperty("arrival")
        String arrival,
        @Schema(name = "carrier name", example = "Airlines")
        @NotBlank
        @JsonProperty("carrier_name")
        String carrierName,
        @Schema(name = "duration in minutes", example = "242")
        @Positive
        @JsonProperty("duration_in_minutes")
        int durationInMinutes,
        @Schema(name = "seat count", example = "120")
        @Positive
        @JsonProperty("seat_count")
        int seatCount
) {
}
