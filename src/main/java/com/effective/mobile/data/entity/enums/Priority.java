package com.effective.mobile.data.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Priority {

    /**
     * Низкая
     */
    LOW("Низкая"),

    /**
     * Средняя
     */
    MEDIUM("Средняя"),

    /**
     * Высокая
     */
    HIGH("Высокая");

    private final String stringValue;
}
