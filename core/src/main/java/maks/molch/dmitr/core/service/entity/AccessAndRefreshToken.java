package maks.molch.dmitr.core.service.entity;

public record AccessAndRefreshToken(
        String accessToken,
        String refreshToken
) {
}
