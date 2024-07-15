package maks.molch.dmitr.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.jwt")
public record JwtConfig(
        String privateKey,
        String publicKey,
        Integer expireTimeInMinutes
) {
}
