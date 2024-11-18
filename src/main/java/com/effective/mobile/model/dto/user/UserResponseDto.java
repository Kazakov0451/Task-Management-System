package com.effective.mobile.model.dto.user;

import com.effective.mobile.data.entity.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Возвращаемое ДТО пользователей")
public class UserResponseDto {

    /**
     * Идентификатор
     */
    @Schema(description = "Идентификатор", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    /**
     * Имя
     */
    @Schema(description = "Имя", requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstName;

    /**
     * Фамилия
     */
    @Schema(description = "Фамилия", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastName;

    /**
     * Почта
     */
    @Schema(description = "Почта", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
}
