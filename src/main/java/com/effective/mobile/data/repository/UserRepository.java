package com.effective.mobile.data.repository;

import com.effective.mobile.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Получаем всех пользователей, по множеству идентификаторов
     * @param executor_ids Множество идентификаторов
     * @return Множество пользователей
     */
    @Query("""
            select us
            from User us
            where us.id in (:executor_ids)
            """)
    Set<User> findAllByIds(Set<Long> executor_ids);

    /**
     * Получить пользователя по почте
     * @param email Почта
     * @return Пользователь
     */
    Optional<User> findByEmail(String email);
}
