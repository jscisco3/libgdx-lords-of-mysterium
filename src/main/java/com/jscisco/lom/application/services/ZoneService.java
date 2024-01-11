package com.jscisco.lom.application.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jscisco.lom.domain.zone.Zone;
import com.jscisco.lom.map.*;
import com.jscisco.lom.random.RNGProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import squidpony.squidmath.RNG;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * ZoneService: Domain service for interacting with the Zone root aggregate.
 */
@Service
public class ZoneService {

    private static final Logger logger = LoggerFactory.getLogger(ZoneService.class);

    private final ObjectMapper objectMapper;
    private final Map<Integer, BuilderChain> builderChains = new HashMap<>();

    @Autowired
    private RNGProvider rngProvider;

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
        IntStream.range(1, depth + 1).forEach(d -> {
            BuilderChain chain = new BuilderChain(d, 80, 80);
            chain.startWith(new DebugStarterBuilder());
            chain.with(new AreaBasedStartingPosition(XStart.CENTER, YStart.CENTER));
            // TODO: Pass in RNG
            chain.build(rngProvider.getRng());
            builderChains.put(d, chain);
            zone.addLevel(chain.getBuildData().getLevel());
        });
        return zone;
    }

    public BuildData getBuildDataAtDepth(int depth) {
        return this.builderChains.get(depth).getBuildData();
    }
}
