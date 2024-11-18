package com.effective.mobile.converter;

import com.effective.mobile.data.entity.Comment;
import com.effective.mobile.data.entity.Task;
import com.effective.mobile.data.entity.Users;
import com.effective.mobile.model.dto.comment.CommentResponseDto;
import com.effective.mobile.model.dto.task.TaskRequestDto;
import com.effective.mobile.model.dto.task.TaskResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class CommentConverter {

    private final UsersConverter usersConverter;

    /**
     * Конвертирует {@link String} в {@link Task}
     *
     * @param text Текст комментария
     * @return ДАО объект
     */
    public Comment toEntity(String text, Task task) {
        if (text == null) {
            return null;
        }

        return Comment.builder()
                .author(Users.builder()
                        .id(1L)
                        .build())
                .text(text)
                .task(task)
                .build();
    }

    /**
     * Конвертирует {@link Comment} в {@link CommentResponseDto}
     *
     * @param comment ДАО объект
     * @return ДТО объект
     */
    public CommentResponseDto toDto(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("Недопустимо передавать null значение");
        }

        return CommentResponseDto.builder()
                .id(comment.getId())
                .createdAt(comment.getCreatedAt())
                .authorBy(usersConverter.toDto(comment.getAuthor()))
                .text(comment.getText())
                .build();
    }
}
