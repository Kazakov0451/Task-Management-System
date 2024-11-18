package com.effective.mobile.model.dto.auth_token;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;

@Schema(description = "Возвращает ДТО с access и refresh токенами")
public record JwtAuthenticationResponseDto(String token, String refreshToken) {
}
