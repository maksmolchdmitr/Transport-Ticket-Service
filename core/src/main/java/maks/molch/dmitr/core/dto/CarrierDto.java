package maks.molch.dmitr.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import maks.molch.dmitr.core.dto.validation.Phone;

public record CarrierDto(
        @NotBlank
        @JsonProperty("name")
        String name,
        @Phone
        @JsonProperty("phone")
        String phone
) {
}
