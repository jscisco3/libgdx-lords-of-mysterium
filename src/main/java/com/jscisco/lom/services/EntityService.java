package com.jscisco.lom.services;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.NPC;
import com.jscisco.lom.map.Level;
import com.jscisco.lom.raws.RawNPC;
import com.jscisco.lom.raws.RawMaster;
import org.springframework.stereotype.Service;

@Service
public class EntityService {

    /**
     * This method will spawn an entity by name on the given level in the given position
     *
     * @param raws
     * @param name
     * @param level
     * @param position
     */
    public void spawnNPC(RawMaster raws, String name, Level level, Position position) {
        // Create entity from the raws
        RawNPC rawNPC = raws.getRaws().getNPC(name);
        NPC e = NPC.from(rawNPC);
        // TODO: Decide how we should add entities to the level. I think via `level.addEntity(...)`
        e.setPosition(position);
        e.setLevel(level);
    }
}
