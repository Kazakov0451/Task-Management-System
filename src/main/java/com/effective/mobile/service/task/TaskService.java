package com.effective.mobile.service.task;

import com.effective.mobile.model.dto.task.TaskRequestDto;
import com.effective.mobile.model.dto.task.TaskResponseDto;

import java.util.List;

public interface TaskService {

    /**
     * Создание новой задачи
     *
     * @param taskDto ДТО для создания задачи
     * @return ДТО созданной задачи
     */
    TaskResponseDto create(TaskRequestDto taskDto);

    /**
     * Редактирование задачи
     *
     * @param taskDto ДТО для редактирования задачи
     * @return ДТО задачи
     */
    TaskResponseDto update(Long taskId, TaskRequestDto taskDto);

    /**
     * Получение списка всех задач
     *
     * @return Список всех задач
     */
    List<TaskResponseDto> getAll();

    /**
     * Удаление задачи
     *
     * @param id Идентификатор удаляемой задачи
     */
    void delete(Long id);
}
