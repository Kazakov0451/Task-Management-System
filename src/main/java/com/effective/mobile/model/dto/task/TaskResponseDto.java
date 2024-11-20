package com.effective.mobile.model.dto.task;

import com.effective.mobile.data.entity.enums.Priority;
import com.effective.mobile.data.entity.enums.Status;
import com.effective.mobile.model.dto.comment.CommentResponseDto;
import com.effective.mobile.model.dto.user.UserResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Возвращаемое ДТО задачи")
public class TaskResponseDto {

    /**
     * Идентификатор
     */
    @Schema(description = "Идентификатор", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    /**
     * Заголовок
     */
    @Schema(description = "Заголовок", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    /**
     * Описание
     */
    @Schema(description = "Описание", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    /**
     * Статус задачи
     */
    @Schema(description = "Статус задачи: WAITING - В ожидании, IN_PROGRESS - В процессе, COMPLETED - Завершено",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private Status status;

    /**
     * Приоритетность задачи
     */
    @Schema(description = "Приоритетность задачи: LOW - Низкая, MEDIUM - Средняя, HIGH - Высокая",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private Priority priority;

    /**
     * Автор задачи
     */
    @Schema(description = "Автор задачи", requiredMode = Schema.RequiredMode.REQUIRED)
    private UserResponseDto author;

    /**
     * Множество исполнителей задачи
     */
    @Schema(description = "Множество исполнителей задачи", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Set<UserResponseDto> executorSet;

    /**
     * Список комментариев
     */
    @Schema(description = "Список комментариев", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<CommentResponseDto> commentList;

}
