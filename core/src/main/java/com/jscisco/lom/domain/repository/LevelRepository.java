package com.jscisco.lom.domain.repository;

import com.jscisco.lom.domain.event.level.LevelEvent;
import com.jscisco.lom.domain.zone.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LevelRepository extends JpaRepository<Level, UUID> {

    @Query("select event from LevelEvent event where event.level.id = :levelId order by event.id desc")
    List<LevelEvent> getEvents(UUID levelId);

}
