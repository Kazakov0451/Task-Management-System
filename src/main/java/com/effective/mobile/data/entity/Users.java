package com.effective.mobile.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Дата создания
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * Имя
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Фамилия
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Почта
     */
    @Column(name = "mail")
    private String mail;

    /**
     * Роль
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    /**
     * Пароль
     */
    @Column(name = "password")
    private String password;

    @PrePersist
    private void setCreatedFields() {
        this.createdAt = LocalDateTime.now();
    }

    @RequiredArgsConstructor
    @Getter
    public enum Role {

        /**
         * Администратор
         */
        ROLE_ADMIN("Администратор"),

        /**
         * Пользователь
         */
        ROLE_USER("Пользователь");

        private final String stringValue;
    }
}
