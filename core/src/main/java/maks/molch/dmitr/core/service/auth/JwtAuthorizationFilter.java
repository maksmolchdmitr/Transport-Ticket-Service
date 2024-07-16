package maks.molch.dmitr.core.service.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

public abstract class JwtAuthorizationFilter extends OncePerRequestFilter {
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        return authHeader == null || !authHeader.startsWith(BEARER_PREFIX)
                || request.getServletPath().equals("/user/login")
                || request.getServletPath().equals("/user/register");
    }
}
