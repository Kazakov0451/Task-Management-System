package com.effective.mobile.data.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Priority {

    /**
     * Низкий
     */
    LOW("Низкий"),

    /**
     * Средний
     */
    MEDIUM("Средний"),

    /**
     * Высокий
     */
    HIGH("Высокий");

    private final String stringValue;
}
