package com.jscisco.lom.domain.repository;

import com.jscisco.lom.domain.entity.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityRepository extends JpaRepository<Entity, Long> {
}
