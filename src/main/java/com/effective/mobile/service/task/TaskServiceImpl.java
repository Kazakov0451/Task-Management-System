package com.effective.mobile.service.task;

import com.effective.mobile.converter.TaskConverter;
import com.effective.mobile.data.entity.Task;
import com.effective.mobile.data.entity.Users;
import com.effective.mobile.data.repository.TaskRepository;
import com.effective.mobile.data.repository.UsersRepository;
import com.effective.mobile.exception.GenericNotFoundException;
import com.effective.mobile.model.dto.task.TaskRequestDto;
import com.effective.mobile.model.dto.task.TaskResponseDto;
import lombok.RequiredArgsConstructor;
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
    private final UsersRepository usersRepository;
    private final TaskRepository taskRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public TaskResponseDto create(TaskRequestDto taskDto) {
        Set<Users> executorSet = new HashSet<>();

        if (!CollectionUtils.isEmpty(taskDto.getExecutor_ids())) {
            executorSet = usersRepository.findAllByIds(taskDto.getExecutor_ids());
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

        Set<Users> executorSet = new HashSet<>(task.getExecutorBy());

        if (!CollectionUtils.isEmpty(taskDto.getExecutor_ids())) {
            executorSet = usersRepository.findAllByIds(taskDto.getExecutor_ids());
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
