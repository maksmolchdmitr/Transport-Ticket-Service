package maks.molch.dmitr.core.service.auth.impl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.repo.TokenRepo;
import maks.molch.dmitr.core.service.auth.JwtAuthorizationFilter;
import maks.molch.dmitr.core.service.auth.JwtService;
import maks.molch.dmitr.core.service.exception.AuthenticationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class JwtAuthorizationFilterImpl extends JwtAuthorizationFilter {
    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtService jwtService;
    private final TokenRepo tokenRepo;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {
        var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            throw new AuthenticationException("Authorization header is invalid");
        }
        var token = authHeader.replace(BEARER_PREFIX, "");
        try {
            if (!tokenRepo.isAliveAccessToken(token)) {
                throw new BadCredentialsException("Invalid access token");
            }
            SecurityContextHolder.getContext().setAuthentication(jwtService.parseToken(token));
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            tokenRepo.setRevoked(token);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
