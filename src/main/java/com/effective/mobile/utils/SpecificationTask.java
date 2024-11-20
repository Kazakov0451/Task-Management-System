package com.effective.mobile.utils;

import com.effective.mobile.data.entity.Task;
import com.effective.mobile.data.entity.User;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationTask {

    public static Specification<Task> findByParam(Object param, SpecificationFilterEnum specificationFilter) {

        if (specificationFilter.equals(SpecificationFilterEnum.AUTHOR)) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("author"), param);

        } else if (specificationFilter.equals(SpecificationFilterEnum.EXECUTOR)) {

            final var executor = (User) param;
            return (root, query, criteriaBuilder) -> {
                Join<Task, User> executorJoin = root.join("executor");
                return criteriaBuilder.equal(executorJoin.get("id"), executor.getId());
            };

        } else if (specificationFilter.equals(SpecificationFilterEnum.STATUS)) {

            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), param);
        } else {

            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("priority"), param);
        }
    }
}
