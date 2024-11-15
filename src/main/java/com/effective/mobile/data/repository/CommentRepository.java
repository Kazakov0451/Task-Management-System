package com.effective.mobile.data.repository;

import com.effective.mobile.data.entity.Comment;
import com.effective.mobile.data.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}