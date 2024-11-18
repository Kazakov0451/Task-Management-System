package com.effective.mobile.exception;

public class GenericNotFoundException extends NullPointerException {

    /**
     * Общее для всех "Не найден" исключение
     *
     * @param id    Идентификатор искомой сущности
     * @param clazz Искомый класс
     */
    public GenericNotFoundException(Long id, Class<?> clazz) {
        super(String.format("Сущность %s с идентификатором %s не найдена", clazz.getCanonicalName(), id));
    }
}
