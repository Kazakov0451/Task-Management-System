package com.effective.mobile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class LoginOrPasswordException extends HttpStatusCodeException {

    /**
     * Общее для всех "Не корректная почта и пароль" исключение
     *
     * @param message Текст ошибки
     * @param statusCode Статус код
     */
    public LoginOrPasswordException(HttpStatus statusCode, String message) {
        super(statusCode, message);
    }
}
