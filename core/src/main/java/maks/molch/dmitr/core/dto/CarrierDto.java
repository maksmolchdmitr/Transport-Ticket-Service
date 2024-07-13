package maks.molch.dmitr.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CarrierDto(
        @JsonProperty("name")
        String name,
        @JsonProperty("phone")
        String phone
) {
}
