package maks.molch.dmitr.core.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserLoginRequestDto(
        @Schema(name = "login", example = "admin")
        @JsonProperty("login")
        String login,
        @Schema(name = "password", example = "admin")
        @JsonProperty("password")
        String password
) {
}
