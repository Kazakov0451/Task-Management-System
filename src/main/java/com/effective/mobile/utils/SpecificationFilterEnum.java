package com.effective.mobile.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SpecificationFilterEnum {

    /**
     * Автор задачи
     */
    AUTHOR("Автор задачи"),

    /**
     * Исполнитель задачи
     */
    EXECUTOR("Исполнитель задачи"),

    /**
     * Статус задачи
     */
    STATUS("Статус задачи"),

    /**
     * Приоритетность задачи
     */
    PRIORITY("Приоритетность задачи");

    private final String stringValue;
}
