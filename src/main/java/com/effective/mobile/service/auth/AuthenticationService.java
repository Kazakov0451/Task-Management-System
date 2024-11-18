package com.effective.mobile.service.auth;

import com.effective.mobile.model.dto.auth_token.RefreshTokenRequest;
import com.effective.mobile.model.dto.auth_token.JwtAuthenticationResponseDto;
import com.effective.mobile.model.dto.user.LoginFromRequestDto;

public interface AuthenticationService {

    /**
     * Авторизация пользователя
     * @param loginDto Данные пользователя (Почта и пароль)
     * @return AccessToken и RefreshToken
     */
    JwtAuthenticationResponseDto login(LoginFromRequestDto loginDto);

    /**
     * Обновление токена
     * @param refreshTokenRequest RefreshToken для обновления AccessToken
     * @return AccessToken и RefreshToken
     */
    JwtAuthenticationResponseDto refreshToken(RefreshTokenRequest refreshTokenRequest);
}
