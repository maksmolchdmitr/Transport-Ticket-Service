package maks.molch.dmitr.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import maks.molch.dmitr.core.dto.validation.Phone;

public record CarrierDto(
        @Schema(name = "name", example = "Airlines")
        @NotBlank
        @JsonProperty("name")
        String name,
        @Schema(name = "phone", example = "+79502138213")
        @Phone
        @JsonProperty("phone")
        String phone
) {
}
