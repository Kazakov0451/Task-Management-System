package com.effective.mobile.converter;

import com.effective.mobile.data.entity.Task;
import com.effective.mobile.data.entity.User;
import com.effective.mobile.model.dto.task.request.TaskRequestDto;
import com.effective.mobile.model.dto.task.TaskResponseDto;
import com.effective.mobile.security.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class TaskConverter {

    private final UserConverter userConverter;
    private final CommentConverter commentConverter;

    /**
     * Конвертирует {@link TaskRequestDto} в {@link Task}
     *
     * @param taskDto ДТО объект
     * @return ДАО объект
     */
    public Task toEntity(TaskRequestDto taskDto, Set<User> executorSet) {
        if (taskDto == null) {
            return null;
        }

        return Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .status(taskDto.getStatus())
                .priority(taskDto.getPriority())
                .executor(executorSet)
                .author(UserDetails.getUser())
                .build();
    }

    /**
     * Конвертирует {@link Task} в {@link TaskResponseDto}
     *
     * @param task ДАО объект
     * @return ДТО объект
     */
    public TaskResponseDto toDto(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Недопустимо передавать null значение");
        }

        return TaskResponseDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .author(userConverter.toDto(task.getAuthor()))
                .executorSet(task.getExecutor().stream()
                        .map(userConverter::toDto)
                        .collect(Collectors.toSet()))
                .commentList(task.getCommentList() != null
                        ? task.getCommentList().stream()
                        .map(commentConverter::toDto)
                        .collect(Collectors.toList())
                        : null)
                .build();
    }
}
