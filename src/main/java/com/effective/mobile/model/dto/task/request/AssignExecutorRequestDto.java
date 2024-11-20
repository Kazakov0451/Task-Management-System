package com.effective.mobile.model.dto.task.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

import java.util.Set;

@Schema(description = "Получаемое ДТО назначения исполнителя задачи")
public record AssignExecutorRequestDto(
        @Schema(description = "Множество идентификаторов исполнителей")
        @NotBlank(message = "Множество идентификаторов должен быть не null и не пустым")
        @NonNull Set<Long> executorSet
) {

}
