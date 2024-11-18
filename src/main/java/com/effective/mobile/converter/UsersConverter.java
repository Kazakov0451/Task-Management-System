package com.effective.mobile.converter;

import com.effective.mobile.data.entity.Comment;
import com.effective.mobile.data.entity.Task;
import com.effective.mobile.data.entity.Users;
import com.effective.mobile.model.dto.task.TaskRequestDto;
import com.effective.mobile.model.dto.task.TaskResponseDto;
import com.effective.mobile.model.dto.users.UsersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class UsersConverter {

    /**
     * Конвертирует {@link TaskRequestDto} в {@link Task}
     *
     * @param taskDto ДТО объект
     * @return ДАО объект
     */
    public Task toEntity(TaskRequestDto taskDto, Set<Users> executorSet, List<Comment> commentList) {
        if (taskDto == null) {
            return null;
        }

        return Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .status(taskDto.getStatus())
                .priority(taskDto.getPriority())
                .executorBy(executorSet)
                .build();
    }

    /**
     * Конвертирует {@link Task} в {@link UsersResponseDto}
     *
     * @param users ДАО объект
     * @return ДТО объект
     */
    public UsersResponseDto toDto(Users users) {
        if (users == null) {
            throw new IllegalArgumentException("Недопустимо передавать null значение");
        }

        return UsersResponseDto.builder()
                .id(users.getId())
                .firstName(users.getFirstName())
                .lastName(users.getLastName())
                .role(users.getRole())
                .mail(users.getMail())
                .build();
    }
}
