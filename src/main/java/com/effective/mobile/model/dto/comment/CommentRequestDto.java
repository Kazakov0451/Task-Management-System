package com.effective.mobile.model.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

@Schema(description = "Получаемое ДТО комментария")
public record CommentRequestDto(
        @Schema(description = "Текст комментария")
        @NotBlank(message = "Текст комментария должен быть не null и не пустым")
        @NonNull String comment) {
}
