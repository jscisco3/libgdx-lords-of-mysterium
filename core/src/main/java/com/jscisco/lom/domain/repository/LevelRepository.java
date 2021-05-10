package com.jscisco.lom.domain.repository;

import com.jscisco.lom.domain.zone.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {

}
