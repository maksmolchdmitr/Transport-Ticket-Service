package maks.molch.dmitr.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDto(
        @JsonProperty("login")
        String login,
        @JsonProperty("password")
        String password,
        @JsonProperty("full_name")
        String fullName
) {
}
