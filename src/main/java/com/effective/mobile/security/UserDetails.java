package com.effective.mobile.security;

import com.effective.mobile.data.entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class UserDetails {

    private UserDetailsService userDetailsService;

    private static UserDetails userDetails;

    /**
     * Конструктор
     */
    public UserDetails(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Инит метод, который позволяет класс сервиса преобразовать в статичный класс
     */
    @PostConstruct
    public void init() {
        userDetails = this;
        userDetails.userDetailsService = this.userDetailsService;
    }

    /**
     * Возвращает сотрудника из БД по id keycloak, авторизованного сотрудника
     *
     * @return Сотрудник из БД
     */
    public static User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
