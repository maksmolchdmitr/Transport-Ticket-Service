package maks.molch.dmitr.core.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserLoginRequestDto(
        @JsonProperty("login")
        String login,
        @JsonProperty("password")
        String password
) {
}
