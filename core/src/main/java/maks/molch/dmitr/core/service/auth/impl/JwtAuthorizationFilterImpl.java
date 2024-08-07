package maks.molch.dmitr.core.service.auth.impl;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.repo.TokenRepo;
import maks.molch.dmitr.core.service.auth.JwtAuthorizationFilter;
import maks.molch.dmitr.core.service.auth.JwtService;
import maks.molch.dmitr.core.service.exception.AuthenticationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class JwtAuthorizationFilterImpl extends JwtAuthorizationFilter {
    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtService jwtService;
    private final TokenRepo tokenRepo;

    @Override
    public @NotNull Mono<Void> filterInternal(ServerWebExchange exchange, @NotNull WebFilterChain chain) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            return Mono.error(new AuthenticationException("Authorization header is invalid"));
        }
        String token = authHeader.replace(BEARER_PREFIX, "");
        try {
            if (!tokenRepo.isAliveAccessToken(token)) {
                return Mono.error(new BadCredentialsException("Invalid access token"));
            }
            return chain.filter(exchange)
                    .contextWrite(context -> ReactiveSecurityContextHolder.withAuthentication(jwtService.parseToken(token)));
        } catch (ExpiredJwtException e) {
            tokenRepo.setRevoked(token);
            exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
            return Mono.empty();
        }
    }
}
