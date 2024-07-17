package maks.molch.dmitr.core.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record UserCreateRequestDto(
        @NotBlank
        @JsonProperty("login")
        String login,
        @NotBlank
        @JsonProperty("password")
        String password,
        @NotBlank
        @JsonProperty("full_name")
        String fullName
) {
}
