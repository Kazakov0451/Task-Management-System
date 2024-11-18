package com.effective.mobile.converter;

import com.effective.mobile.data.entity.Task;
import com.effective.mobile.data.entity.User;
import com.effective.mobile.model.dto.user.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserConverter {

    /**
     * Конвертирует {@link Task} в {@link UserResponseDto}
     *
     * @param user ДАО объект
     * @return ДТО объект
     */
    public UserResponseDto toDto(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Недопустимо передавать null значение");
        }

        return UserResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
