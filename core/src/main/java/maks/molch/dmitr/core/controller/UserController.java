package maks.molch.dmitr.core.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.controller.exception.ErrorMessage;
import maks.molch.dmitr.core.dto.request.UserCreateRequestDto;
import maks.molch.dmitr.core.dto.response.UserResponseDto;
import maks.molch.dmitr.core.mapper.UserMapper;
import maks.molch.dmitr.core.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Tag(name = "User Controller")
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Register user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User was successfully registered!",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "User with such login was already registered!",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class))
            )
    })
    @PostMapping("/register")
    public Mono<UserResponseDto> register(@Valid @RequestBody UserCreateRequestDto createRequestDto) {
        return Mono.fromSupplier(() -> {
            var userTable = userMapper.toUser(createRequestDto);
            var userRecord = userService.register(userTable);
            return userMapper.toDto(userRecord);
        });
    }
}
