package com.effective.mobile.data.repository;

import com.effective.mobile.data.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface UsersRepository extends JpaRepository<Users, Long> {

    /**
     * Получаем всех пользователей, по множеству идентификаторов
     * @param executor_ids Множество идентификаторов
     * @return Множество пользователей
     */
    @Query("""
            select u
            from Users u
            where u.id in (:executor_ids)
            """)
    Set<Users> findAllByIds(Set<Long> executor_ids);
}
