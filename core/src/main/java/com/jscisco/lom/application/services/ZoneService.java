package com.jscisco.lom.application.services;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.entity.EntityFactory;
import com.jscisco.lom.domain.entity.NPC;
import com.jscisco.lom.domain.event.Event;
import com.jscisco.lom.domain.event.level.Generated;
import com.jscisco.lom.domain.event.level.LevelEvent;
import com.jscisco.lom.domain.event.level.LevelTransitionFeatureAdded;
import com.jscisco.lom.domain.event.level.TileExplored;
import com.jscisco.lom.domain.repository.LevelEventRepository;
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

import javax.annotation.Nonnull;
import javax.transaction.Transactional;
import java.util.List;
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
    private final LevelEventRepository levelEventRepository;


    @Autowired
    public ZoneService(LevelRepository levelRepository, ZoneRepository zoneRepository, LevelEventRepository levelEventRepository) {
        this.levelRepository = levelRepository;
        this.zoneRepository = zoneRepository;
        this.levelEventRepository = levelEventRepository;
    }

    public Zone getZone(UUID zoneId) {
        logger.info("Loading zone...");
        Zone zone = zoneRepository.getById(zoneId);
        Hibernate.initialize(zone);
        Hibernate.initialize(zone.getLevels());
        for (Level level : zone.getLevels()) {
            List<LevelEvent> events = levelEventRepository.findAllByLevelIdOrderByIdAsc(level.getId());
            level.processEvents(events);
            logger.info("Moving entities...");
            level.getEntities().forEach(e -> e.move(e.getPosition()));
        }
        return zone;
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
            LevelTransitionFeatureAdded descent = new LevelTransitionFeatureAdded(below.getId(), Position.of(5, 5), true);
            descent.setLevelId(above.getId());
            descent.process(above);
            levelEventRepository.save(descent);

            LevelTransitionFeatureAdded ascent = new LevelTransitionFeatureAdded(above.getId(), Position.of(6, 5), false);
            ascent.setLevelId(below.getId());
            ascent.process(below);
            levelEventRepository.save(ascent);
        }
        // Populate levels ?
//        for (Level level : zone.getLevels()) {
//            for (int i = 0; i < 20; i++) {
//                NPC golem = EntityFactory.golem();
//                level.addEntityAtPosition(golem, level.getEmptyTile(golem));
//            }
//        }
        return zone;
    }

    public Zone saveZone(Zone zone) {
        return zoneRepository.save(zone);
    }

    public Level createLevel(@Nonnull Zone zone, int width, int height, LevelGeneratorStrategy.Strategy strategy) {
        Level level = new Level(width, height);
        Generated event = new Generated();
        event.setStrategy(strategy);
        event.setSeed(0xDEADBEEFL);
        event.setLevelId(level.getId());
        event.process(level);
        levelEventRepository.save(event);
        zone.addLevel(level);
        return level;
    }

    @Deprecated
    public Level createLevel(UUID zoneId, int width, int height, LevelGeneratorStrategy.Strategy strategy) {
        Zone zone = zoneRepository.getById(zoneId);
        Level level = new Level(width, height);
        Generated event = new Generated();
        event.setStrategy(strategy);
        event.setSeed(0xDEADBEEFL);
        event.setLevelId(level.getId());
        event.process(level);
        levelEventRepository.save(event);
        zone.addLevel(level);
//        saveLevel(level);
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
        List<LevelEvent> events = levelEventRepository.findAllByLevelIdOrderByIdAsc(levelId);
        Hibernate.initialize(level.getItems());
        // Run through all events
        level.processEvents(events);
        // Initialize all entity positions
        level.getEntities().forEach(e -> e.move(e.getPosition()));
//        level.getItems().forEach(i -> {
//            logger.info(i.toString());
//            assert i.getPosition() != null;
//            level.getTileAt(i.getPosition()).addItem(i);
//        });
        return level;
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
        levelEventRepository.save(event);
    }

    public void saveLevelEvents(List<LevelEvent> events) {
        levelEventRepository.saveAll(events);
    }

    public void onNotify(Event event) {
        if (event instanceof TileExplored) {
            TileExplored te = (TileExplored) event;
            levelEventRepository.save(te);
        }
    }
}
