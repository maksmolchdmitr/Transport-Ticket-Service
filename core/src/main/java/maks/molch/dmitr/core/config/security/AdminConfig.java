package maks.molch.dmitr.core.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.admin")
public record AdminConfig(
        String login,
        String password
) {
}
