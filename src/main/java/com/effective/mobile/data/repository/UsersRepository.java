package com.effective.mobile.data.repository;

import com.effective.mobile.data.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
