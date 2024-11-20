package com.effective.mobile.data.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {

    /**
     * Администратор
     */
    ROLE_ADMIN("Администратор"),

    /**
     * Пользователь
     */
    ROLE_USER("Пользователь");

    private final String stringValue;
}
