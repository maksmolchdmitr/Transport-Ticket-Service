package maks.molch.dmitr.core.service.auth.entity;

public record AccessAndRefreshToken(
        String accessToken,
        String refreshToken
) {
}
