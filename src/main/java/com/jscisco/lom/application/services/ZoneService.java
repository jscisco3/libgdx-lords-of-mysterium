package com.jscisco.lom.application.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.entity.EntityFactory;
import com.jscisco.lom.domain.entity.NPC;
import com.jscisco.lom.domain.event.Event;
import com.jscisco.lom.domain.event.level.Generated;
import com.jscisco.lom.domain.event.level.LevelEvent;
import com.jscisco.lom.domain.event.level.LevelTransitionFeatureAdded;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.LevelGeneratorStrategy;
import com.jscisco.lom.domain.zone.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * ZoneService: Domain service for interacting with the Zone root aggregate.
 */
@Service
public class ZoneService {

    private static final Logger logger = LoggerFactory.getLogger(ZoneService.class);

    private final ObjectMapper objectMapper;

    @Autowired
    public ZoneService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Zone getZone(UUID zoneId) {
        throw new UnsupportedOperationException();
    }

    /**
     * This method generates the skeleton of a zone.
     * That is, it is responsible for creating the empty levels, persisting them to generate ids,
     * and then linking the levels together via LevelTransitionFeatures
     *
     * @return
     */
    public Zone createZone() {
        throw new UnsupportedOperationException();
    }

    public Zone createZone(int depth) {
        Zone zone = new Zone();
//        zone = zoneRepository.save(zone);
        for (int i = 0; i < depth; i++) {
            createLevel(zone, 60, 60, LevelGeneratorStrategy.Strategy.GENERIC);
        }
        // Link levels
        // Descending...
        for (int i = 0; i < depth - 1; i++) {
            Level above = zone.getLevels().get(i);
            Level below = zone.getLevels().get(i + 1);
            // Generate stairs down
            LevelTransitionFeatureAdded descent = new LevelTransitionFeatureAdded(below.getId(), Position.of(5, 5), Position.of(6, 5), true);
            descent.setLevelId(above.getId());
            descent.process(above);

            LevelTransitionFeatureAdded ascent = new LevelTransitionFeatureAdded(above.getId(), Position.of(6, 5), Position.of(5, 5), false);
            ascent.setLevelId(below.getId());
            ascent.process(below);
        }
        // Populate levels ?
        for (Level level : zone.getLevels()) {
            for (int i = 0; i < 20; i++) {
                NPC golem = EntityFactory.golem();
                level.addEntityAtPosition(golem, level.getEmptyTile(golem));
            }
        }
        return zone;
    }

    public Zone saveZone(Zone zone) {
        throw new UnsupportedOperationException();
    }

    public Level createLevel(Zone zone, int width, int height, LevelGeneratorStrategy.Strategy strategy) {
        Level level = new Level(width, height);
        Generated event = new Generated();
        event.setStrategy(strategy);
        event.setSeed(0xDEADBEEFL);
        event.setLevelId(level.getId());
        event.process(level);
        zone.addLevel(level);
        return level;
    }

    @Deprecated
    public Level createLevel(UUID zoneId, int width, int height, LevelGeneratorStrategy.Strategy strategy) {
        Level level = new Level(width, height);
        Generated event = new Generated();
        event.setStrategy(strategy);
        event.setSeed(0xDEADBEEFL);
        event.setLevelId(level.getId());
        event.process(level);
//        saveLevel(level);
        return level;
    }

    public Level saveLevel(Level level) {
        throw new UnsupportedOperationException();
    }

    public Level loadLevel(UUID levelId) {
        throw new UnsupportedOperationException();
    }

    public void changeLevel(Entity e, Zone zone, UUID toLevelId, Position to) {
        logger.info("Changing level....");
        Level toLevel = zone.getLevelById(toLevelId);
        Level currentLevel = e.getLevel();
        logger.info("Zone:" + zone);
        logger.info("To Level: " + toLevel);
        logger.info("From Level: " + currentLevel);
        currentLevel.removeEntity(e);
        toLevel.addEntityAtPosition(e, to);
    }

    public void saveLevelEvent(LevelEvent event) {
        throw new UnsupportedOperationException();
    }

    public void saveLevelEvents(List<LevelEvent> events) {
        throw new UnsupportedOperationException();
    }

    public void onNotify(Event event) {
    }
}
