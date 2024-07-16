package maks.molch.dmitr.core.service.auth;

import org.springframework.security.core.Authentication;

public interface JwtService {
    String generateAccessToken(Authentication authData);

    String generateRefreshToken(Authentication authData);

    Authentication parseToken(String jwt);
}
