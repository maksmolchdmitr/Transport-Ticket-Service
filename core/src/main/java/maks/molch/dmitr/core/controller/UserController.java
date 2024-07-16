package maks.molch.dmitr.core.controller;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.dto.AuthenticationResponseDto;
import maks.molch.dmitr.core.dto.UserCreateRequestDto;
import maks.molch.dmitr.core.dto.UserDto;
import maks.molch.dmitr.core.dto.UserLoginDto;
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
    public UserDto register(@RequestBody UserCreateRequestDto createRequestDto) {
        var userTable = userMapper.toUser(createRequestDto);
        var userRecord = userService.register(userTable);
        return userMapper.toDto(userRecord);
    }

    @PostMapping("/login")
    public AuthenticationResponseDto login(@RequestBody UserLoginDto userLoginDto) {
        var token = authenticationService.authenticateAndGenerateToken(userLoginDto.login(), userLoginDto.password());
        return tokenMapper.toDto(token);
    }
}
