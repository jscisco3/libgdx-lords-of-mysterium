package com.jscisco.lom.application.services;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.event.level.DescentFeatureAdded;
import com.jscisco.lom.domain.event.level.Generated;
import com.jscisco.lom.domain.event.level.LevelTransitionFeatureAdded;
import com.jscisco.lom.domain.repository.LevelRepository;
import com.jscisco.lom.domain.repository.ZoneRepository;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.LevelGeneratorStrategy;
import com.jscisco.lom.domain.zone.Zone;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

/**
 * ZoneService: Domain service for interacting with the Zone root aggregate.
 */
@Service
@Transactional
public class ZoneService {

    private static final Logger logger = LoggerFactory.getLogger(ZoneService.class);
    private final ZoneRepository zoneRepository;
    private final LevelRepository levelRepository;

    @Autowired
    public ZoneService(LevelRepository levelRepository, ZoneRepository zoneRepository) {
        this.levelRepository = levelRepository;
        this.zoneRepository = zoneRepository;
    }

    public Zone getZone(Long zoneId) {
        return zoneRepository.getById(zoneId);
    }

    /**
     * This method generates the skeleton of a zone.
     * That is, it is responsible for creating the empty levels, persisting them to generate ids,
     * and then linking the levels together via LevelTransitionFeatures
     *
     * @return
     */
    public Zone createZone() {
        Zone zone = new Zone();
        return zoneRepository.save(zone);
    }

    public Zone createZone(int depth) {
        Zone zone = new Zone();
        zone = zoneRepository.save(zone);
        for (int i = 0; i < depth; i++) {
            createLevel(zone, 60, 60, LevelGeneratorStrategy.Strategy.EMPTY);
        }
        // Link levels
        // Descending...
        for (int i = 0; i < depth - 1; i++) {
            Level above = zone.getLevels().get(i);
            Level below = zone.getLevels().get(i + 1);
            // Generate stairs down
            LevelTransitionFeatureAdded descent = new LevelTransitionFeatureAdded(below.getId(), Position.of(5, 5), true);
            LevelTransitionFeatureAdded ascent = new LevelTransitionFeatureAdded(below.getId(), Position.of(6, 5), false);
            above.addEvent(descent);
            below.addEvent(ascent);
            descent.process();
            ascent.process();
            saveLevel(above);
            saveLevel(below);
        }
        return zone;
    }

    public Level createLevel(Zone zone, int width, int height, LevelGeneratorStrategy.Strategy strategy) {
        return createLevel(zone.getId(), width, height, strategy);
    }

    public Level createLevel(Long zoneId, int width, int height, LevelGeneratorStrategy.Strategy strategy) {
        Zone zone = zoneRepository.getById(zoneId);
        Level level = new Level(width, height);
        Generated event = new Generated();
        event.setStrategy(strategy);
        event.setSeed(0xDEADBEEFL);
        level.addEvent(event);
        level.processEvents();
        zone.addLevel(level);
        saveLevel(level);
        return level;
    }

    public Level saveLevel(Level level) {
        logger.info("saving level with # entites: " + level.getEntities().size());
        logger.info("saving level with # items: " + level.getItems().size());
        return levelRepository.save(level);
    }

    public Level loadLevel(UUID levelId) {
        logger.info("Loading level with id: " + levelId);
        Level level = levelRepository.findById(levelId).get();
        Hibernate.initialize(level.getItems());
        // Run through all events
        level.processEvents();
        // Initialize all entity positions
        level.getEntities().forEach(e -> e.move(e.getPosition()));
//        level.getItems().forEach(i -> {
//            logger.info(i.toString());
//            assert i.getPosition() != null;
//            level.getTileAt(i.getPosition()).addItem(i);
//        });
        return level;
    }

    public boolean changeLevel(Entity e, UUID levelId, Position to) {
        Level nextLevel = loadLevel(levelId);
        Level currentLevel = e.getLevel();
        currentLevel.removeEntity(e);
        // TODO: May have to move any colliding entities
        nextLevel.addEntityAtPosition(e, to);
        levelRepository.save(currentLevel);
        levelRepository.save(nextLevel);
        return true;
    }

    public Long getNextLevelId() {
        return zoneRepository.nextLevelId();
    }

}
