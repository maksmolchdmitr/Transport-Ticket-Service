package maks.molch.dmitr.core.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.dto.request.UserCreateRequestDto;
import maks.molch.dmitr.core.dto.request.UserLoginRequestDto;
import maks.molch.dmitr.core.dto.response.AuthenticationResponseDto;
import maks.molch.dmitr.core.dto.response.UserResponseDto;
import maks.molch.dmitr.core.mapper.TokenMapper;
import maks.molch.dmitr.core.mapper.UserMapper;
import maks.molch.dmitr.core.service.UserService;
import maks.molch.dmitr.core.service.auth.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthenticationService authenticationService;
    private final TokenMapper tokenMapper;

    @PostMapping("/register")
    public UserResponseDto register(@Valid @RequestBody UserCreateRequestDto createRequestDto) {
        var userTable = userMapper.toUser(createRequestDto);
        var userRecord = userService.register(userTable);
        return userMapper.toDto(userRecord);
    }

    @PostMapping("/login")
    public AuthenticationResponseDto login(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto) {
        var token = authenticationService.authenticateAndGenerateToken(userLoginRequestDto.login(), userLoginRequestDto.password());
        return tokenMapper.toDto(token);
    }
}
