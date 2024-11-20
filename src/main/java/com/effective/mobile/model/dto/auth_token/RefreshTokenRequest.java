package com.effective.mobile.model.dto.auth_token;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

@Schema(description = "Получаемое ДТО для обновления токена")
public record RefreshTokenRequest(@Schema(description = "RefreshToken")
                                  @NotBlank(message = "RefreshToken должен быть не null и не пустым")
                                  @NonNull
                                  String refreshToken) {
}
