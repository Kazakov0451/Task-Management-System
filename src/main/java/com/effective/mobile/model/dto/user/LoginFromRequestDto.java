package com.effective.mobile.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Получаемое ДТО для авторизации")
public record LoginFromRequestDto(@Schema(description = "Почта") String email,
                                  @Schema(description = "Пароль") String password) {
}
