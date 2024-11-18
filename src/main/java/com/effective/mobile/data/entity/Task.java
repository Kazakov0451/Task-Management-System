package com.effective.mobile.data.entity;

import com.effective.mobile.data.entity.editable_interface.EditableUpdate;
import com.effective.mobile.data.entity.enums.Priority;
import com.effective.mobile.data.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task", schema = "public")
public class Task implements EditableUpdate<Task> {

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
    private User authorBy;

    /**
     * Исполнители данной задачи
     */
    @ManyToMany
    @JoinTable(
            name = "task_executor",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> executorBy;

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
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Comment> commentList;

    @PrePersist
    private void setCreatedFields() {
        this.createdAt = LocalDateTime.now();
        this.status = this.status != null ? this.status : Status.WAITING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task update(Task updated) {
        return Task.builder()
                .id(getId())
                .createdAt(getCreatedAt())
                .authorBy(getAuthorBy())
                .executorBy(updated.getExecutorBy())
                .title(updated.getTitle())
                .description(updated.getDescription())
                .status(updated.getStatus())
                .priority(updated.getPriority())
                .commentList(getCommentList())
                .build();
    }
}
