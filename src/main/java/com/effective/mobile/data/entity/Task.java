package com.effective.mobile.data.entity;

import com.effective.mobile.data.entity.enums.Priority;
import com.effective.mobile.data.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task")
public class Task {

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
     * Автор задачи
     */
    @ManyToOne
    @JoinColumn(name = "author_by")
    private Users authorBy;

    /**
     * Исполнитель задачи
     */
    @ManyToOne
    @JoinColumn(name = "executor_by")
    private Users executorBy;

    /**
     * Заголовок задачи
     */
    @Column(name = "title")
    private String title;

    /**
     * Описание
     */
    @Column(name = "description")
    private String description;

    /**
     * Статус
     */
    @Column(name = "status")
    private Status status;

    /**
     * Приоритет
     */
    @Column(name = "priority")
    private Priority priority;

    /**
     * Список комментариев
     */
    @OneToMany(mappedBy = "task")
    private List<Comment> commentList;

    @PrePersist
    private void setCreatedFields() {
        this.createdAt = LocalDateTime.now();
        this.status = this.status != null ? this.status : Status.WAITING;
    }
}
