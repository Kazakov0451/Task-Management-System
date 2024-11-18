package com.effective.mobile.data.entity;

import com.effective.mobile.data.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

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

    /**
     * Множество задач, для которых является исполнителем
     */
    @ManyToMany(mappedBy = "executorBy")
    private Set<Task> tasksExecutor;

    @PrePersist
    private void setCreatedFields() {
        this.createdAt = LocalDateTime.now();
    }
}
