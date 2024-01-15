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

    @Autowired
    public ZoneService(ObjectMapper objectMapper,
                       RNGProvider rngProvider,
                       EntityService entityService,
                       RawMaster raws
                       ) {
        this.objectMapper = objectMapper;
        this.rngProvider = rngProvider;
        this.entityService = entityService;
        this.raws = raws;
    }

    public Zone createZone(int depth) {
        Zone zone = new Zone();
        IntStream.range(1, depth + 1).forEach(d -> {
            BuilderChain chain = new BuilderChain(d, 80, 80);
            chain.startWith(new DebugStarterBuilder());
            chain.with(new AreaBasedStartingPosition(XStart.CENTER, YStart.CENTER));
            // TODO: Pass in RNG
            chain.build(rngProvider.getRng(), this.raws);
            builderChains.put(d, chain);
            zone.addLevel(chain.getBuildData().getLevel());

            Position startingPosition = chain.getBuildData().getStartingPosition();
            Position npcSpawnPosition = startingPosition.add(Position.of(2, 0));
            entityService.spawnNPC(this.raws, "Golem", chain.getBuildData().getLevel(), npcSpawnPosition);


        });
        return zone;
    }

    public BuildData getBuildDataAtDepth(int depth) {
        return this.builderChains.get(depth).getBuildData();
    }
}
