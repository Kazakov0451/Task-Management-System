package com.effective.mobile.model.dto.task.request;

import com.effective.mobile.data.entity.enums.Priority;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

@Schema(description = "Получаемое ДТО смена приоритета")
public record ChangePriorityRequestDto(
        @Schema(description = "Приоритетность задачи: LOW - Низкая, MEDIUM - Средняя, HIGH - Высокая")
        @NotBlank(message = "Приоритетность задачи должен быть не null и не пустым")
        @NonNull Priority priority
) {

}
