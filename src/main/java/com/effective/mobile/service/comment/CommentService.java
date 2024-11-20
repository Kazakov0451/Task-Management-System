package com.effective.mobile.service.comment;

import com.effective.mobile.model.dto.comment.CommentRequestDto;
import com.effective.mobile.model.dto.comment.CommentResponseDto;

public interface CommentService {

    /**
     * Создание нового комментария
     *
     * @param commentDto ДТО с Текстом для создания Комментария
     * @return ДТО созданного комментария
     */
    CommentResponseDto create(Long taskId, CommentRequestDto commentDto);
}
