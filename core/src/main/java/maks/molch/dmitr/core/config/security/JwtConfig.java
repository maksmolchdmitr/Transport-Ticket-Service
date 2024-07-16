package maks.molch.dmitr.core.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.jwt")
public record JwtConfig(
        String privateKey,
        String publicKey,
        Integer expireTimeAccessTokenInMinutes,
        Integer expireTimeRefreshTokenInDays
) {
}
