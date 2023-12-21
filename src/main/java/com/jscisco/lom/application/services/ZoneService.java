package com.jscisco.lom.application.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jscisco.lom.domain.zone.Zone;
import com.jscisco.lom.map.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import squidpony.squidmath.RNG;

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
     * This method generates the skeleton of a zone. That is, it is responsible for creating the empty levels,
     * persisting them to generate ids, and then linking the levels together via LevelTransitionFeatures
     *
     * @return
     */
    public Zone createZone() {
        throw new UnsupportedOperationException();
    }

    public Zone createZone(int depth) {
        Zone zone = new Zone();
        // zone = zoneRepository.save(zone);
        // TODO: Builder
        BuilderChain chain = new BuilderChain(1, 80, 80);
        chain.startWith(new DebugStarterBuilder());
        chain.with(new AreaBasedStartingPosition(XStart.CENTER, YStart.CENTER));
        chain.build(new RNG());
        zone.addLevel(chain.getBuildData().getLevel());
        return zone;
    }
}
