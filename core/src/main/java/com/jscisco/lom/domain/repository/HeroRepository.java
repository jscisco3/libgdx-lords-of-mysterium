package com.jscisco.lom.domain.repository;

import com.jscisco.lom.domain.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<Hero, Long> {
}
