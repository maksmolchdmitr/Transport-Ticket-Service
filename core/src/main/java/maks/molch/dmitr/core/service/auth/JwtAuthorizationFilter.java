package maks.molch.dmitr.core.service.auth;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

public abstract class JwtAuthorizationFilter implements WebFilter {
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    public @NotNull Mono<Void> filter(ServerWebExchange exchange, @NotNull WebFilterChain chain) {
        System.out.println("Filter: " + exchange + " " + chain);
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String path = exchange.getRequest().getURI().getPath();
        if (shouldNotFilter(authHeader, path)) {
            System.out.println("Should not filter! with path: " + path);
            return chain.filter(exchange);
        }
        return filterInternal(exchange, chain);
    }

    protected boolean shouldNotFilter(String authHeader, String path) {
        return (authHeader == null || !authHeader.startsWith(BEARER_PREFIX))
                || !path.startsWith("/swagger-ui")
                || !path.startsWith("/webjars/swagger-ui/index.html")
                || !path.startsWith("/v3/api-docs");
    }

    protected abstract Mono<Void> filterInternal(ServerWebExchange exchange, WebFilterChain chain);
}
