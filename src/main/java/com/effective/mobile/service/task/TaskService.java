package com.effective.mobile.service.task;

import com.effective.mobile.data.entity.enums.Priority;
import com.effective.mobile.data.entity.enums.Status;
import com.effective.mobile.model.dto.task.request.ChangePriorityRequestDto;
import com.effective.mobile.model.dto.task.request.ChangeStatusRequestDto;
import com.effective.mobile.model.dto.task.request.TaskRequestDto;
import com.effective.mobile.model.dto.task.TaskResponseDto;
import com.effective.mobile.model.dto.task.request.AssignExecutorRequestDto;

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
     * Назначить исполнителя задачи
     *
     * @param assignExecutorDto ДТО для назначения исполнителей у задачи
     * @return ДТО задачи
     */
    TaskResponseDto assignExecutor(Long taskId, AssignExecutorRequestDto assignExecutorDto);

    /**
     * Смена статуса задачи
     *
     * @param changeStatusDto ДТО для редактирования статуса задачи
     * @return ДТО задачи
     */
    TaskResponseDto changeStatus(Long taskId, ChangeStatusRequestDto changeStatusDto);

    /**
     * Смена приоритета задачи
     *
     * @param changePriorityDto ДТО для редактирования приоритета задачи
     * @return ДТО задачи
     */
    TaskResponseDto changePriority(Long taskId, ChangePriorityRequestDto changePriorityDto);

    /**
     * Получения всех задач, используя фильтрацию и пагинацию для администраторов
     *
     * @return Список всех задач
     */
    List<TaskResponseDto> getAll(Long authorId, Long executorId, Status status, Priority priority,
                                 Integer page, Integer size);

    /**
     * Удаление задачи
     *
     * @param id Идентификатор удаляемой задачи
     */
    void delete(Long id);
}
