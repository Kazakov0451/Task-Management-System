package com.effective.mobile.data.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {

    /**
     * Администратор
     */
    ADMIN("Администратор"),

    /**
     * Пользователь
     */
    USER("Пользователь");

    private final String stringValue;
}
