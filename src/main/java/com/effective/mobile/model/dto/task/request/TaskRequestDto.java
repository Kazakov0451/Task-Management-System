package com.effective.mobile.model.dto.task.request;

import com.effective.mobile.data.entity.enums.Priority;
import com.effective.mobile.data.entity.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Получаемое ДТО задачи")
public class TaskRequestDto {

    /**
     * Заголовок
     */
    @NonNull
    @NotBlank(message = "Заголовок должен быть не null и не пустым")
    @Schema(description = "Заголовок")
    private String title;

    /**
     * Описание
     */
    @Schema(description = "Описание")
    private String description;

    /**
     * Статус задачи
     */
    @Schema(description = "Статус задачи: WAITING - В ожидании, IN_PROGRESS - В процессе, COMPLETED - Завершено")
    private Status status;

    /**
     * Приоритетность задачи
     */
    @Schema(description = "Приоритетность задачи: LOW - Низкая, MEDIUM - Средняя, HIGH - Высокая")
    private Priority priority;

    /**
     * Множество идентификаторов, которые исполняют задачу
     */
    @Schema(description = "Множество идентификаторов, которые исполняют задачу")
    private Set<Long> executor_ids;

}
