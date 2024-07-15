package maks.molch.dmitr.core.controller.auth;

import org.springframework.security.core.Authentication;

public interface JwtService {
    String generateToken(Authentication authentication);

    Authentication parseToken(String jwt);
}
