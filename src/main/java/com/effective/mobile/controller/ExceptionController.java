package com.effective.mobile.controller;

import com.effective.mobile.exception.PermissionDeniedException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

@ControllerAdvice
public class ExceptionController {

    /**
     * Возвращает текс ошибки и статус код
     *
     * @param e класс {@link PermissionDeniedException}
     * @return Тело ошибки
     */
    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity handleException(PermissionDeniedException e) {
        return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
    }

    /**
     * Возвращает текс ошибки и статус код
     *
     * @param e Класс {@link ConstraintViolationException}
     * @return Тело ошибки
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleExceptionValidated(ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage().split(":")[1]);
    }

    /**
     * Возвращает текс ошибки и статус код
     *
     * @param e Класс {@link HttpStatusCodeException}
     * @return Тело ошибки
     */
    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity handleExceptionStatusCode(HttpStatusCodeException e) {
        return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
    }

    /**
     * Возвращает текс ошибки и статус код
     *
     * @param e Класс {@link MethodArgumentNotValidException}
     * @return Тело ошибки
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleExceptionStatusCode(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getAllErrors().get(0).getDefaultMessage());
    }
}
