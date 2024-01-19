package com.jscisco.lom.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.zone.Zone;
import com.jscisco.lom.map.*;
import com.jscisco.lom.random.RNGProvider;
import com.jscisco.lom.raws.RawMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * ZoneService: Domain service for interacting with the Zone root aggregate.
 */
@Service
public class ZoneService {

    private static final Logger logger = LoggerFactory.getLogger(ZoneService.class);

    private final ObjectMapper objectMapper;
    private final Map<Integer, BuilderChain> builderChains = new HashMap<>();

    private final RNGProvider rngProvider;

    private final EntityService entityService;

    private final RawMaster raws;

    private final Spawner spawner;

    @Autowired
    public ZoneService(ObjectMapper objectMapper,
                       RNGProvider rngProvider,
                       EntityService entityService,
                       RawMaster raws,
                       Spawner spawner
    ) {
        this.objectMapper = objectMapper;
        this.rngProvider = rngProvider;
        this.entityService = entityService;
        this.raws = raws;
        this.spawner = spawner;
    }

    public Zone createZone(int depth) {
        Zone zone = new Zone();
        IntStream.range(1, depth + 1).forEach(d -> {
            logger.info("Creating level at depth {}", d);
            BuilderChain chain = new BuilderChain(d, 80, 80);
            chain.startWith(new DebugStarterBuilder());
//            chain.startWith(new BSPBuilder());
            chain.with(new RoomBasedSpawner(spawner));
            chain.with(new AreaBasedStartingPosition(XStart.CENTER, YStart.CENTER));
            // TODO: Pass in RNG
            chain.build(rngProvider.getRng(), this.raws);
            builderChains.put(d, chain);
            zone.addLevel(chain.getBuildData().getLevel());
        });
        return zone;
    }

    public BuildData getBuildDataAtDepth(int depth) {
        return this.builderChains.get(depth).getBuildData();
    }
}
