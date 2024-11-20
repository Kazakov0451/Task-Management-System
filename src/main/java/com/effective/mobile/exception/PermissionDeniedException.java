package com.effective.mobile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PermissionDeniedException extends HttpStatusCodeException {

    /**
     * Ошибка общая для всех "Отказано в доступе"
     *
     * @param message Текст ошибки
     * @param statusCode  Статус код
     */
    public PermissionDeniedException(HttpStatus statusCode, String message) {
        super(statusCode, message);
    }

}
