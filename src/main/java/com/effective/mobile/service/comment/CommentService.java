package com.effective.mobile.service.comment;

import com.effective.mobile.model.dto.comment.CommentResponseDto;

public interface CommentService {

    /**
     * Создание нового комментария
     *
     * @param commentText Текст для создания Комментария
     * @return ДТО созданного комментария
     */
    CommentResponseDto create(Long taskId, String commentText);
}