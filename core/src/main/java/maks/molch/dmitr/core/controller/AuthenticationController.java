package maks.molch.dmitr.core.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.dto.request.UserLoginRequestDto;
import maks.molch.dmitr.core.dto.response.AuthenticationResponseDto;
import maks.molch.dmitr.core.mapper.TokenMapper;
import maks.molch.dmitr.core.service.auth.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Tag(name = "Authentication Controller")
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final TokenMapper tokenMapper;

    @Operation(summary = "Regenerate access token")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = @Content(schema = @Schema(implementation = AuthenticationResponseDto.class))
            )
    })
    @PostMapping("/refresh-token")
    public Mono<AuthenticationResponseDto> refreshToken(ServerWebExchange exchange) {
        return authenticationService.refreshToken(exchange)
                .map(tokenMapper::toDto);
    }

    @Operation(summary = "Login user and generate access and refresh tokens")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = @Content(schema = @Schema(implementation = AuthenticationResponseDto.class))
            )
    })
    @PostMapping("/login")
    public Mono<AuthenticationResponseDto> login(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto) {
        return authenticationService.authenticateAndGenerateToken(userLoginRequestDto.login(), userLoginRequestDto.password())
                .map(tokenMapper::toDto);
    }
}
