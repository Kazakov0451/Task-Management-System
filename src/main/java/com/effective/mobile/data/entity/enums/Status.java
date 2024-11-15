package com.effective.mobile.data.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Status {

    /**
     * В ожидании
     */
    WAITING("В ожидании"),

    /**
     * В процессе
     */
    IN_PROGRESS("В процессе"),

    /**
     * Завершено
     */
    COMPLETED("Завершено");

    private final String stringValue;
}
