package maks.molch.dmitr.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserLoginDto(
        @JsonProperty("login")
        String login,
        @JsonProperty("password")
        String password
) {
}
