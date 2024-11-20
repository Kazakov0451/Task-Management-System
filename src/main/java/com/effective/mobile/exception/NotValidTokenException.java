package com.effective.mobile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class NotValidTokenException extends HttpStatusCodeException {

    /**
     * Ошибка не валидный токен
     * @param message Текст ошибки
     * @param statusCode Статус код
     */
    public NotValidTokenException(HttpStatus statusCode, String message) {
        super(statusCode, message);
    }
}
