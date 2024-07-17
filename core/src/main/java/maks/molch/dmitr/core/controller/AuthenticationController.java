package maks.molch.dmitr.core.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.dto.response.AuthenticationResponseDto;
import maks.molch.dmitr.core.mapper.TokenMapper;
import maks.molch.dmitr.core.service.auth.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final TokenMapper tokenMapper;

    @PostMapping("/refresh-token")
    public AuthenticationResponseDto refreshToken(HttpServletRequest request, HttpServletResponse response) {
        var token = authenticationService.refreshToken(request, response);
        return tokenMapper.toDto(token);
    }
}
