package maks.molch.dmitr.core.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record UserCreateRequestDto(
        @Schema(name = "login", example = "vasilii2002")
        @NotBlank
        @JsonProperty("login")
        String login,
        @Schema(name = "password", example = "pass1234")
        @NotBlank
        @JsonProperty("password")
        String password,
        @Schema(name = "full name", example = "Ivanov Vasilii Yegorovich")
        @NotBlank
        @JsonProperty("full_name")
        String fullName
) {
}
