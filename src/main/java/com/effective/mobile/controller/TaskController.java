package com.effective.mobile.controller;

import com.effective.mobile.data.entity.enums.Priority;
import com.effective.mobile.data.entity.enums.Status;
import com.effective.mobile.model.dto.comment.CommentRequestDto;
import com.effective.mobile.model.dto.comment.CommentResponseDto;
import com.effective.mobile.model.dto.task.TaskResponseDto;
import com.effective.mobile.model.dto.task.request.AssignExecutorRequestDto;
import com.effective.mobile.model.dto.task.request.ChangePriorityRequestDto;
import com.effective.mobile.model.dto.task.request.ChangeStatusRequestDto;
import com.effective.mobile.model.dto.task.request.TaskRequestDto;
import com.effective.mobile.service.comment.CommentService;
import com.effective.mobile.service.task.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("api/v1/task")
@RequestMapping("/task")
@Validated
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@Tag(name = "Задачи", description = "Отображение всех задач, добавления новых, обновление и удаление")
public class TaskController {

    private final TaskService taskService;
    private final CommentService commentService;

    /**
     * Создание задачи
     *
     * @param taskDto ДТО задачи
     * @return Задача
     */
    @Operation(summary = "Создание задачи")
    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN')")
    public TaskResponseDto create(@RequestBody @Valid TaskRequestDto taskDto) {
        return taskService.create(taskDto);
    }

    /**
     * Редактирование задачи
     *
     * @param taskDto ДТО задачи
     * @return Задача
     */
    @Operation(summary = "Редактирование задачи")
    @PutMapping("/{taskId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public TaskResponseDto update(@Parameter(description = "Идентификатор задачи")
                                  @PathVariable @Min(value = 1, message = "Id должен быть больше нуля") Long taskId,
                                  @RequestBody TaskRequestDto taskDto) {
        return taskService.update(taskId, taskDto);
    }

    /**
     * Назначения исполнителей у задачи
     *
     * @param assignExecutorDto ДТО для назначения исполнителей задачи
     * @return Задача
     */
    @Operation(summary = "Редактирование задачи")
    @PutMapping("/{taskId}/assign_executor")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public TaskResponseDto assignExecutor(@Parameter(description = "Идентификатор задачи")
                                          @PathVariable @Min(value = 1, message = "Id должен быть больше нуля")
                                          Long taskId,
                                          @RequestBody AssignExecutorRequestDto assignExecutorDto) {
        return taskService.assignExecutor(taskId, assignExecutorDto);
    }

    /**
     * Смена статуса задачи
     *
     * @param changeStatusDto ДТО для смены статуса задачи
     * @return Задача
     */
    @Operation(summary = "Смена статуса задачи")
    @PutMapping("/{taskId}/change_status")
    public TaskResponseDto changeStatus(@Parameter(description = "Идентификатор задачи")
                                        @PathVariable @Min(value = 1, message = "Id должен быть больше нуля")
                                        Long taskId,
                                        @RequestBody ChangeStatusRequestDto changeStatusDto) {
        return taskService.changeStatus(taskId, changeStatusDto);
    }

    /**
     * Смена приоритетности задачи
     *
     * @param changePriorityDto ДТО для смены приоритетности задачи
     * @return Задача
     */
    @Operation(summary = "Смена приоритетности задачи")
    @PutMapping("/{taskId}/change_priority")
    @PreAuthorize("hasRole('ADMIN')")
    public TaskResponseDto changeStatus(@Parameter(description = "Идентификатор задачи")
                                        @PathVariable @Min(value = 1, message = "Id должен быть больше нуля")
                                        Long taskId,
                                        @RequestBody ChangePriorityRequestDto changePriorityDto) {
        return taskService.changePriority(taskId, changePriorityDto);
    }

    /**
     * Получения всех задач, используя фильтрацию и пагинацию для администраторов
     *
     * @return Список задач
     */
    @Operation(summary = "Получения списка всех задач")
    @GetMapping()
    public List<TaskResponseDto> getAll(
            @Parameter(description = "Параметр для фильтрации по автору задачи (Идентификатор автора)")
            @RequestParam(required = false) @Min(value = 1, message = "Id должен быть больше нуля") Long authorId,
            @Parameter(description = "Параметр для фильтрации по исполнителю задачи (Идентификатор исполнителя)")
            @RequestParam(required = false) @Min(value = 1, message = "Id должен быть больше нуля") Long executorId,
            @Parameter(description = "Параметр для фильтрации по статусу")
            @RequestParam(required = false) Status status,
            @Parameter(description = "Параметр для фильтрации по приоритетности")
            @RequestParam(required = false) Priority priority,
            @Parameter(description = "Номер страницы")
            @RequestParam(value = "page", required = false)
            @Min(value = 1, message = "Страница должен быть больше нуля") Integer page,
            @Parameter(description = "Кол-во задач на страницы")
            @RequestParam(value = "size", defaultValue = "10", required = false)
            @Min(value = 1, message = "Кол-во задач на страницы должно быть больше 0")
            @Max(value = 30, message = "Кол-во задач на страницы должно быть меньше 30") Integer size) {
        return taskService.getAll(authorId, executorId, status, priority, page, size);
    }

    /**
     * Удаление задачи
     *
     * @param taskId Идентификатор задачи
     */
    @Operation(summary = "Удаление задачи")
    @DeleteMapping("/{taskId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void update(@Parameter(description = "Идентификатор задачи")
                       @PathVariable @Min(value = 1, message = "Id должен быть больше нуля") Long taskId) {
        taskService.delete(taskId);
    }

    /**
     * Создание комментария к задаче
     *
     * @param taskId     Идентификатор задачи
     * @param commentDto ДТО c Текстом комментария
     * @return Созданный комментарий
     */
    @Operation(summary = "Создание комментария к задаче")
    @PostMapping("/{taskId}/comment")
    public CommentResponseDto createComment(@Parameter(description = "Идентификатор задачи")
                                            @PathVariable @Min(value = 1, message = "Id должен быть больше нуля")
                                            Long taskId,
                                            @RequestBody CommentRequestDto commentDto) {
        return commentService.create(taskId, commentDto);
    }
}
