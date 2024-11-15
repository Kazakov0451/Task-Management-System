package com.effective.mobile.data.repository;

import com.effective.mobile.data.entity.Task;
import com.effective.mobile.data.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
