package com.effective.mobile.service.task;

import com.effective.mobile.converter.TaskConverter;
import com.effective.mobile.data.entity.Task;
import com.effective.mobile.data.entity.User;
import com.effective.mobile.data.entity.enums.Priority;
import com.effective.mobile.data.entity.enums.Role;
import com.effective.mobile.data.entity.enums.Status;
import com.effective.mobile.data.repository.TaskRepository;
import com.effective.mobile.data.repository.UserRepository;
import com.effective.mobile.exception.GenericNotFoundException;
import com.effective.mobile.exception.PermissionDeniedException;
import com.effective.mobile.model.dto.task.TaskResponseDto;
import com.effective.mobile.model.dto.task.request.AssignExecutorRequestDto;
import com.effective.mobile.model.dto.task.request.ChangePriorityRequestDto;
import com.effective.mobile.model.dto.task.request.ChangeStatusRequestDto;
import com.effective.mobile.model.dto.task.request.TaskRequestDto;
import com.effective.mobile.security.UserDetails;
import com.effective.mobile.utils.SpecificationFilterEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.effective.mobile.utils.SpecificationTask.findByParam;

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

        Set<User> executorSet = new HashSet<>(task.getExecutor());

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
    public TaskResponseDto assignExecutor(Long taskId, AssignExecutorRequestDto assignExecutorDto) {
        final var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new GenericNotFoundException(taskId, Task.class));

        final var executorSet = userRepository.findAllByIds(assignExecutorDto.executorSet());

        task.setExecutor(executorSet);

        final var taskUpdated = taskRepository.save(task);

        return taskConverter.toDto(taskUpdated);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResponseDto changeStatus(Long taskId, ChangeStatusRequestDto changeStatusDto) {
        final var user = userRepository.findByEmail(UserDetails.getUser().getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с таким логином не существует"));

        final var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new GenericNotFoundException(taskId, Task.class));

        if (user.getRole().equals(Role.ROLE_USER) && !task.getExecutor().contains(user)) {
            throw new PermissionDeniedException(HttpStatus.BAD_REQUEST,
                    "Недостаточно прав для смены статуса не у своей задачи");
        }

        task.setStatus(changeStatusDto.status());

        return taskConverter.toDto(taskRepository.save(task));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResponseDto changePriority(Long taskId, ChangePriorityRequestDto changePriorityDto) {
        final var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new GenericNotFoundException(taskId, Task.class));

        task.setPriority(changePriorityDto.priority());

        return taskConverter.toDto(taskRepository.save(task));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TaskResponseDto> getAll(Long authorId, Long executorId, Status status, Priority priority,
                                        Integer page, Integer size) {
        final var user = userRepository.findByEmail(UserDetails.getUser().getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с таким логином не существует"));

        if (user.getRole().equals(Role.ROLE_ADMIN)) {
            var specification = filterParametersToSpecification(authorId, executorId, status, priority);

            return taskRepository.findAll(specification, PageRequest.of(page - 1, size)).stream()
                    .map(taskConverter::toDto)
                    .collect(Collectors.toList());
        } else {
            var specification = filterParametersToSpecification(null, user.getId(), status, priority);

            return taskRepository.findAll(specification, PageRequest.of(page - 1, size)).stream()
                    .map(taskConverter::toDto)
                    .collect(Collectors.toList());
        }
    }

    private Specification<Task> filterParametersToSpecification(Long authorId, Long executorId,
                                                                Status status, Priority priority) {
        User author = new User();
        User executor = new User();

        if (authorId != null) {
            author = userRepository.findById(authorId)
                    .orElseThrow(() -> new GenericNotFoundException(authorId, User.class));
        }

        if (executorId != null) {
            executor = userRepository.findById(executorId)
                    .orElseThrow(() -> new GenericNotFoundException(executorId, User.class));
        }

        List<Specification<Task>> specifications = new ArrayList<>();
        specifications.add(authorId != null ? findByParam(author, SpecificationFilterEnum.AUTHOR) : null);
        specifications.add(executorId != null ? findByParam(executor, SpecificationFilterEnum.EXECUTOR) : null);
        specifications.add(status != null ? findByParam(status, SpecificationFilterEnum.STATUS) : null);
        specifications.add(priority != null ? findByParam(priority, SpecificationFilterEnum.PRIORITY) : null);

        return specifications.stream().filter(Objects::nonNull).reduce(Specification::and)
                .orElse((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
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
