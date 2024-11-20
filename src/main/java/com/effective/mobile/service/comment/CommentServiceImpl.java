package com.effective.mobile.service.comment;

import com.effective.mobile.converter.CommentConverter;
import com.effective.mobile.data.entity.Task;
import com.effective.mobile.data.entity.enums.Role;
import com.effective.mobile.data.repository.CommentRepository;
import com.effective.mobile.data.repository.TaskRepository;
import com.effective.mobile.data.repository.UserRepository;
import com.effective.mobile.exception.GenericNotFoundException;
import com.effective.mobile.exception.PermissionDeniedException;
import com.effective.mobile.model.dto.comment.CommentRequestDto;
import com.effective.mobile.model.dto.comment.CommentResponseDto;
import com.effective.mobile.security.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CommentConverter commentConverter;

    /**
     * {@inheritDoc}
     */
    @Override
    public CommentResponseDto create(Long taskId, CommentRequestDto commentDto) {
        final var user = userRepository.findByEmail(UserDetails.getUser().getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с таким логином не существует"));

        final var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new GenericNotFoundException(taskId, Task.class));

        if (user.getRole().equals(Role.ROLE_USER) && !task.getExecutor().contains(user)) {
            throw new PermissionDeniedException(HttpStatus.BAD_REQUEST,
                    "Недостаточно прав для создания комментария не для своей задачи");
        }

        final var comment = commentRepository.save(commentConverter.toEntity(commentDto, task));

        return commentConverter.toDto(comment);
    }
}
