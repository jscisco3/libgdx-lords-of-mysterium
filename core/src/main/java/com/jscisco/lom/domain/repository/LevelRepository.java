package com.jscisco.lom.domain.repository;

import com.jscisco.lom.domain.zone.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LevelRepository extends JpaRepository<Level, UUID> {

}
