package com.jscisco.lom.map;

import com.jscisco.lom.RandomTable;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.NPC;
import com.jscisco.lom.random.RNGProvider;
import com.jscisco.lom.raws.RawMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import squidpony.squidmath.RNG;

import java.util.ArrayList;
import java.util.List;

@Component
public class Spawner {
    private final Logger logger = LoggerFactory.getLogger(Spawner.class);
    private RawMaster raws;
    private final RNG rng;

    @Autowired
    public Spawner(RawMaster raws, RNGProvider rngProvider) {
        this.raws = raws;
        this.rng = rngProvider.getRng();
    }

    public void spawnRoom(Level level, Rect room) {
        // Get list of possible points (floors)
        List<Position> region = new ArrayList<>();
        room.points().forEach(p -> {
            if (level.getTile(p).getFeature() == Feature.FLOOR) {
                region.add(p);
            }
        });
        // Pass them to spawnRegion, which is responsible for
        // actually spawning entities.
        spawnRegion(level, region);
    }

    /**
     * For a given region (a list of valid positions for spawning), we will spawn the appropriate NPC or Item.
     *
     * @param level  The level we are spawning in
     * @param region The valid positions for spawning
     */
    public void spawnRegion(Level level, List<Position> region) {
        int num_spawns = 4;
        if (region.isEmpty()) {
            return;
        }
        RandomTable table = this.raws.getNpcSpawnTableForDepth(level.depth);
        for (int i = 0; i < num_spawns; i++) {
            Position p = rng.getRandomElement(region);
            // Get random entity from Raws
            String toSpawn = table.roll(this.rng);
            NPC npc = NPC.from(
                    raws.getRaws().getNPC(toSpawn)
            );
            level.addEntity(npc, p);
            region.remove(p);
            logger.info("Spawning {} at {}", npc.getName(), p);
        }
    }

    private void spawnNPC() {

    }

    private void spawnItem() {

    }

}
