package maks.molch.dmitr.core.service.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import maks.molch.dmitr.core.service.auth.entity.AccessAndRefreshToken;
import maks.molch.dmitr.core.service.exception.AuthenticationException;
import maks.molch.dmitr.core.service.exception.EntityNotFoundException;

public interface AuthenticationService {
    AccessAndRefreshToken authenticateAndGenerateToken(String username, String password) throws AuthenticationException, EntityNotFoundException;

    AccessAndRefreshToken refreshToken(HttpServletRequest request, HttpServletResponse response);
}
