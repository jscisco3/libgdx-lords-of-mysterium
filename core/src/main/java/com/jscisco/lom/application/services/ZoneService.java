package com.jscisco.lom.application.services;

import com.jscisco.lom.domain.repository.LevelRepository;
import com.jscisco.lom.domain.repository.ZoneRepository;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.LevelGeneratorStrategy;
import com.jscisco.lom.domain.zone.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    public Zone createZone() {
        Zone zone = new Zone();
        return zoneRepository.save(zone);
    }

    public Level createLevel(Long zoneId, int width, int height, LevelGeneratorStrategy strategy) {
        Zone zone = zoneRepository.getById(zoneId);
        Level level = new Level(width, height, strategy);
        zone.addLevel(level);
        return levelRepository.save(level);
    }

    public Level saveLevel(Level level) {
        logger.info("saving level with # entites: " + level.getEntities().size());
        return levelRepository.save(level);
    }

    public Level loadLevel(Long levelId) {
        Level level = levelRepository.findById(levelId).get();
        // Initialize all entity positions
        level.getEntities().forEach(e -> e.move(e.getPosition()));
        return level;
    }

}
