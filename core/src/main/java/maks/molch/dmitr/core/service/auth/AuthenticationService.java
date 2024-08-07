package maks.molch.dmitr.core.service.auth;

import maks.molch.dmitr.core.service.auth.entity.AccessAndRefreshToken;
import maks.molch.dmitr.core.service.exception.AuthenticationException;
import maks.molch.dmitr.core.service.exception.EntityNotFoundException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public interface AuthenticationService {
    Mono<AccessAndRefreshToken> authenticateAndGenerateToken(String username, String password) throws AuthenticationException, EntityNotFoundException;

    Mono<AccessAndRefreshToken> refreshToken(ServerWebExchange exchange);
}
