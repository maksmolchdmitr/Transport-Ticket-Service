package maks.molch.dmitr.core.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponseDto(
        @JsonProperty("login")
        String login,
        @JsonProperty("full_name")
        String fullName
) {
}
