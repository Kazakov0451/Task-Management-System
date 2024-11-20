package com.effective.mobile.model.dto.task.request;

import com.effective.mobile.data.entity.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

@Schema(description = "Получаемое ДТО смена статуса")
public record ChangeStatusRequestDto(
        @Schema(description = "Статус задачи: WAITING - В ожидании, IN_PROGRESS - В процессе, COMPLETED - Завершено")
        @NotBlank(message = "Статус задачи должен быть не null и не пустым")
        @NonNull Status status
) {

}
