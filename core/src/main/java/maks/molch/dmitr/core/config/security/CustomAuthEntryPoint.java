package maks.molch.dmitr.core.config.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CustomAuthEntryPoint implements ServerAuthenticationEntryPoint {
    private final HttpStatusServerEntryPoint delegate = new HttpStatusServerEntryPoint(org.springframework.http.HttpStatus.UNAUTHORIZED);

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        return delegate.commence(exchange, e);
    }
}
