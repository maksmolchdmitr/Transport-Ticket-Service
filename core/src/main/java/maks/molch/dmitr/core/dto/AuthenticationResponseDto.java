package maks.molch.dmitr.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthenticationResponseDto(
        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("refresh_token")
        String refreshToken
) {
}
