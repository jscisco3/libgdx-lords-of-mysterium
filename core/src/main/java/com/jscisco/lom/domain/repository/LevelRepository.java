package com.jscisco.lom.domain.repository;

import com.jscisco.lom.domain.event.level.LevelEvent;
import com.jscisco.lom.domain.zone.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {

    @Query("select event from LevelEvent event where event.level.id = :levelId order by event.id desc")
    List<LevelEvent> getEvents(Long levelId);

}
