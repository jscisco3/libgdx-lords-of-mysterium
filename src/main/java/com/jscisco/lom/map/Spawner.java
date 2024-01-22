package com.jscisco.lom.map;

import com.jscisco.lom.RandomTable;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.NPC;
import com.jscisco.lom.domain.item.Item;
import com.jscisco.lom.random.RNGProvider;
import com.jscisco.lom.raws.RawMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import squidpony.squidmath.RNG;
import squidpony.tileset.SquareRoomsWithRandomRects;

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
        int num_spawns = 1;
        if (region.isEmpty()) {
            return;
        }
        RandomTable npcTable = this.raws.getNpcSpawnTableForDepth(level.depth);
        RandomTable itemTable = this.raws.getItemSpawnTableForDepth(level.depth);
        for (int i = 0; i < num_spawns; i++) {
            Position p = rng.getRandomElement(region);
            // Get random entity from Raws
            String spawned;
            if (this.rng.nextBoolean()) {
                spawned = spawnRandomNpc(npcTable, p, level);
            } else {
                spawned = spawnRandomItem(itemTable, p, level);
            }
            region.remove(p);
            logger.info("Spawning {} at {}", spawned, p);
        }
    }

    private String spawnRandomNpc(RandomTable npcTable, Position p, Level level) {
        String toSpawn = npcTable.roll(this.rng);
        spawnNPC(toSpawn, p, level);
        return toSpawn;
    }

    private void spawnNPC(String name, Position p, Level level) {
        NPC npc = NPC.from(raws.getRaws().getNPC(name));
        level.addEntity(npc, p);
    }

    private String spawnRandomItem(RandomTable itemTable, Position p, Level level) {
        String toSpawn = itemTable.roll(this.rng);
        spawnItem(toSpawn, p, level);
        return toSpawn;
    }

    private void spawnItem(String name, Position p, Level level) {
        Item item = Item.from(raws.getRaws().getItem(name));
        level.addItem(item, p);
    }

}
