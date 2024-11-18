package com.effective.mobile.service.comment;

import com.effective.mobile.converter.CommentConverter;
import com.effective.mobile.data.entity.Task;
import com.effective.mobile.data.repository.CommentRepository;
import com.effective.mobile.data.repository.TaskRepository;
import com.effective.mobile.exception.GenericNotFoundException;
import com.effective.mobile.model.dto.comment.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final CommentConverter commentConverter;

    /**
     * {@inheritDoc}
     */
    @Override
    public CommentResponseDto create(Long taskId, String commentText) {
        final var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new GenericNotFoundException(taskId, Task.class));
        final var comment = commentRepository.save(commentConverter.toEntity(commentText, task));

        return commentConverter.toDto(comment);
    }
}
