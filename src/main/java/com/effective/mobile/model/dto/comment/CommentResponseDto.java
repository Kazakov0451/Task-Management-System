package com.effective.mobile.model.dto.comment;

import com.effective.mobile.model.dto.user.UserResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Возвращаемое ДТО комментария")
public class CommentResponseDto {

    /**
     * Идентификатор
     */
    @Schema(description = "Идентификатор", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    /**
     * Когда создали
     */
    @Schema(description = "Когда создали", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createdAt;

    /**
     * Автор комментария
     */
    @Schema(description = "Автор комментария", requiredMode = Schema.RequiredMode.REQUIRED)
    private UserResponseDto authorBy;

    /**
     * Текст комментария
     */
    @Schema(description = "Текст комментария", requiredMode = Schema.RequiredMode.REQUIRED)
    private String text;
}
