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
@Table(name = "comment")
public class Comment {

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
     * Задача
     */
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    /**
     * Автор комментария
     */
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Users author;

    /**
     * Текст комментария
     */
    @Column(name = "text")
    private String text;

    @PrePersist
    private void setCreatedFields() {
        this.createdAt = LocalDateTime.now();
    }
}
