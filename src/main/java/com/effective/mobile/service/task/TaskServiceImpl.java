package com.effective.mobile.service.task;

import com.effective.mobile.converter.TaskConverter;
import com.effective.mobile.data.entity.Task;
import com.effective.mobile.data.entity.User;
import com.effective.mobile.data.repository.TaskRepository;
import com.effective.mobile.data.repository.UserRepository;
import com.effective.mobile.exception.GenericNotFoundException;
import com.effective.mobile.model.dto.task.TaskRequestDto;
import com.effective.mobile.model.dto.task.TaskResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskConverter taskConverter;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public TaskResponseDto create(TaskRequestDto taskDto) {
        Set<User> executorSet = new HashSet<>();

        var a = SecurityContextHolder.getContext().getAuthentication();

        if (!CollectionUtils.isEmpty(taskDto.getExecutor_ids())) {
            executorSet = userRepository.findAllByIds(taskDto.getExecutor_ids());
        }

        final var task = taskRepository.save(taskConverter.toEntity(taskDto, executorSet));

        return taskConverter.toDto(task);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public TaskResponseDto update(Long taskId, TaskRequestDto taskDto) {
        final var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new GenericNotFoundException(taskId, Task.class));

        Set<User> executorSet = new HashSet<>(task.getExecutorBy());

        if (!CollectionUtils.isEmpty(taskDto.getExecutor_ids())) {
            executorSet = userRepository.findAllByIds(taskDto.getExecutor_ids());
        }

        final var taskUpdated = taskRepository.save(task.update(taskConverter.toEntity(taskDto, executorSet)));

        return taskConverter.toDto(taskUpdated);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TaskResponseDto> getAll() {
        return taskRepository.findAll().stream()
                .map(taskConverter::toDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        final var task = taskRepository.findById(id)
                .orElseThrow(() -> new GenericNotFoundException(id, Task.class));

        taskRepository.delete(task);
    }
}
