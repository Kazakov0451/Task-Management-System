package com.effective.mobile.controller;

import com.effective.mobile.model.dto.auth_token.RefreshTokenRequest;
import com.effective.mobile.model.dto.auth_token.JwtAuthenticationResponseDto;
import com.effective.mobile.model.dto.user.LoginFromRequestDto;
import com.effective.mobile.service.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/v1/login")
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Авторизация", description = "Позволяет аутентифицировать пользователя по логину(Email)"
        + " и паролю, и получать Access и Refresh токены")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @Operation(summary = "Получение Access и Refresh токенов по логину(Почта) и паролю")
    public JwtAuthenticationResponseDto login(@RequestBody @Valid LoginFromRequestDto loginDto) {
        return authenticationService.login(loginDto);
    }

    @PostMapping("/refresh")
    @Operation(summary = "Получение AccessToken и RefreshToken по RefreshToken")
    public JwtAuthenticationResponseDto refreshToken(@RequestBody @Valid RefreshTokenRequest refreshDto) {
        return authenticationService.refreshToken(refreshDto);
    }
}
