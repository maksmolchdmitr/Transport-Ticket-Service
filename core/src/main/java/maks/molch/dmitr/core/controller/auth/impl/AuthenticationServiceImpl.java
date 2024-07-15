package maks.molch.dmitr.core.controller.auth.impl;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.controller.auth.AuthenticationService;
import maks.molch.dmitr.core.controller.auth.JwtService;
import maks.molch.dmitr.core.service.exception.AuthenticationException;
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

    @Override
    public String authenticateAndGenerateToken(String username, String password) throws AuthenticationException {
        try {
            var authentication = authenticate(new UsernamePasswordAuthenticationToken(username, password));
            var jwt = jwtService.generateToken(authentication);
            return BEARER_PREFIX + jwt;
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            throw new AuthenticationException("Authentication failed!");
        }
    }

    private Authentication authenticate(Authentication authentication) throws UsernameNotFoundException {
        var username = authentication.getName();
        var password = String.valueOf(authentication.getCredentials());

        var userDetails = userDetailsService.loadUserByUsername(username);
        if (passwordEncoder.matches(userDetails.getPassword(), password)) {
            return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
        }
        throw new BadCredentialsException("Bad credentials");
    }
}
