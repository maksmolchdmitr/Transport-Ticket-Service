package maks.molch.dmitr.core.service.auth.impl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.service.auth.JwtAuthorizationFilter;
import maks.molch.dmitr.core.service.auth.JwtService;
import maks.molch.dmitr.core.service.exception.AuthenticationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class JwtAuthorizationFilterImpl extends JwtAuthorizationFilter {
    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {
        try {
            var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
                var jwt = authHeader.replace(BEARER_PREFIX, "");
                SecurityContextHolder.getContext().setAuthentication(jwtService.parseToken(jwt));
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }
}
