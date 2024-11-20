package com.effective.mobile.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

@Schema(description = "Получаемое ДТО для авторизации")
public record LoginFromRequestDto(@Schema(description = "Почта")
                                  @NotBlank(message = "Почта должна быть не null и не пустым")
                                  @NonNull String email,
                                  @Schema(description = "Пароль")
                                  @NotBlank(message = "Пароль должен быть не null и не пустым")
                                  @NonNull String password) {
}
