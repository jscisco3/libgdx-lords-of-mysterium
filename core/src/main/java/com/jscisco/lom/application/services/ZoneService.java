package com.jscisco.lom.application.services;

import com.jscisco.lom.domain.repository.LevelRepository;
import com.jscisco.lom.domain.repository.ZoneRepository;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.LevelGeneratorStrategy;
import com.jscisco.lom.domain.zone.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * ZoneService: Domain service for interacting with the Zone root aggregate.
 */
@Service
@Transactional
public class ZoneService {

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
//        zoneRepository.save(zone);
        return levelRepository.save(level);
    }

}
