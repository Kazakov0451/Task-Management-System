package com.effective.mobile.model.dto.auth_token;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Получаемое ДТО для обновления токена")
public record RefreshTokenRequest(@Schema(description = "RefreshToken", name = "token") String refreshToken) {
}
