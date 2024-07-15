package maks.molch.dmitr.core.controller.auth;

import maks.molch.dmitr.core.service.exception.AuthenticationException;

public interface AuthenticationService {
    String authenticateAndGenerateToken(String username, String password) throws AuthenticationException;
}
