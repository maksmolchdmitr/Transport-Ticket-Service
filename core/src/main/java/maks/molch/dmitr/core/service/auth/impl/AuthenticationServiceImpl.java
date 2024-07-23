package maks.molch.dmitr.core.service.auth.impl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.mapper.TokenMapper;
import maks.molch.dmitr.core.repo.TokenRepo;
import maks.molch.dmitr.core.service.auth.AuthenticationService;
import maks.molch.dmitr.core.service.auth.JwtService;
import maks.molch.dmitr.core.service.auth.entity.AccessAndRefreshToken;
import maks.molch.dmitr.core.service.auth.entity.Token;
import maks.molch.dmitr.core.service.exception.AuthenticationException;
import maks.molch.dmitr.core.service.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final String BEARER_PREFIX = "Bearer ";

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenRepo tokenRepo;
    private final TokenMapper tokenMapper;

    @Override
    public AccessAndRefreshToken authenticateAndGenerateToken(String username, String password) throws AuthenticationException, EntityNotFoundException {
        try {
            var authData = authenticate(new UsernamePasswordAuthenticationToken(username, password));
            tokenRepo.setAllRevokedByUserIdWithoutRefreshToken(authData.getName());
            var accessToken = jwtService.generateAccessToken(authData);
            tokenRepo.save(tokenMapper.toRecord(new Token(accessToken, username, Token.Type.ACCESS_TOKEN)));
            var refreshToken = jwtService.generateRefreshToken(authData);
            tokenRepo.save(tokenMapper.toRecord(new Token(refreshToken, username, Token.Type.REFRESH_TOKEN)));
            return new AccessAndRefreshToken(
                    BEARER_PREFIX + accessToken,
                    BEARER_PREFIX + refreshToken
            );
        } catch (UsernameNotFoundException e) {
            throw new EntityNotFoundException("Username not found!");
        } catch (BadCredentialsException | SignatureException e) {
            throw new AuthenticationException("Bad credentials!");
        }
    }

    @Override
    public AccessAndRefreshToken refreshToken(HttpServletRequest request, HttpServletResponse response) {
        var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        var refreshToken = authHeader.replace(BEARER_PREFIX, "");
        try {
            if (!tokenRepo.isAliveRefreshToken(refreshToken)) {
                throw new BadCredentialsException("Invalid refresh token");
            }
            var userAuth = jwtService.parseToken(refreshToken);
            tokenRepo.setAllRevokedByUserIdWithoutRefreshToken(userAuth.getName());
            var newAccessToken = jwtService.generateAccessToken(userAuth);
            tokenRepo.save(tokenMapper.toRecord(new Token(newAccessToken, userAuth.getName(), Token.Type.ACCESS_TOKEN)));
            return new AccessAndRefreshToken(
                    BEARER_PREFIX + newAccessToken,
                    BEARER_PREFIX + refreshToken
            );
        } catch (UsernameNotFoundException | BadCredentialsException ignored) {
        } catch (ExpiredJwtException e) {
            tokenRepo.setRevoked(refreshToken);
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return null;
    }

    private UsernamePasswordAuthenticationToken authenticate(Authentication authentication)
            throws UsernameNotFoundException, BadCredentialsException {
        var username = authentication.getName();
        var password = String.valueOf(authentication.getCredentials());

        var userDetails = userDetailsService.loadUserByUsername(username);
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
        }
        throw new BadCredentialsException("Bad credentials");
    }
}
