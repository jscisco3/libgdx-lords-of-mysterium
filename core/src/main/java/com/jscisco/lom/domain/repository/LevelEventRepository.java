package com.jscisco.lom.domain.repository;

import com.jscisco.lom.domain.event.level.LevelEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LevelEventRepository extends JpaRepository<LevelEvent, Long> {

    List<LevelEvent> findAllByLevelIdOrderByIdAsc(UUID levelId);

}
