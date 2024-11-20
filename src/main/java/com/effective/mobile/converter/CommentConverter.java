package com.effective.mobile.converter;

import com.effective.mobile.data.entity.Comment;
import com.effective.mobile.data.entity.Task;
import com.effective.mobile.model.dto.comment.CommentRequestDto;
import com.effective.mobile.model.dto.comment.CommentResponseDto;
import com.effective.mobile.security.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentConverter {

    private final UserConverter userConverter;

    /**
     * Конвертирует {@link String} в {@link Task}
     *
     * @param commentDto ДТО с Текстом комментария
     * @return ДАО объект
     */
    public Comment toEntity(CommentRequestDto commentDto, Task task) {
        if (commentDto == null) {
            return null;
        }

        return Comment.builder()
                .author(UserDetails.getUser())
                .text(commentDto.comment())
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
                .authorBy(userConverter.toDto(comment.getAuthor()))
                .text(comment.getText())
                .build();
    }
}
