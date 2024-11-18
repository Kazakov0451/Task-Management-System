package com.effective.mobile.controller;

import com.effective.mobile.model.dto.comment.CommentResponseDto;
import com.effective.mobile.model.dto.task.TaskRequestDto;
import com.effective.mobile.model.dto.task.TaskResponseDto;
import com.effective.mobile.service.comment.CommentService;
import com.effective.mobile.service.task.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("api/v1/task")
@RequestMapping("/task")
@RequiredArgsConstructor
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
    public TaskResponseDto create(@RequestBody TaskRequestDto taskDto) {
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
    public TaskResponseDto update(@Parameter(description = "Идентификатор задачи")
                                  @PathVariable Long taskId,
                                  @RequestBody TaskRequestDto taskDto) {
        return taskService.update(taskId, taskDto);
    }

    /**
     * Получения всех задач
     *
     * @return Список задач
     */
    @Operation(summary = "Получения всех задач")
    @GetMapping()
    public List<TaskResponseDto> getAll() {
        return taskService.getAll();
    }

    /**
     * Удаление задачи
     *
     * @param taskId Идентификатор задачи
     */
    @Operation(summary = "Удаление задачи")
    @DeleteMapping("/{taskId}")
    public void update(@Parameter(description = "Идентификатор задачи")
                       @PathVariable Long taskId) {
        taskService.delete(taskId);
    }

    /**
     * Создание комментария к задаче
     *
     * @param taskId      Идентификатор задачи
     * @param commentText Текст комментария
     * @return Созданный комментарий
     */
    @Operation(summary = "Создание комментария к задаче")
    @PostMapping("/{taskId}/comment")
    public CommentResponseDto createComment(@Parameter(description = "Идентификатор задачи")
                                            @PathVariable Long taskId,
                                            @RequestBody String commentText) {
        return commentService.create(taskId, commentText);
    }
}
