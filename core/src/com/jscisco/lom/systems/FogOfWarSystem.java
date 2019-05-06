package com.jscisco.lom.systems;

import com.artemis.Aspect;
import com.artemis.BaseSystem;
import com.artemis.systems.IteratingSystem;
import com.jscisco.lom.components.FOVComponent;
import com.jscisco.lom.dungeon.Dungeon;
import squidpony.squidgrid.FOV;

public class FogOfWarSystem extends IteratingSystem {

    private Dungeon dungeon;
    private FOV fovCalculator = new FOV();

    public FogOfWarSystem(Dungeon dungeon) {
        super(Aspect.all(FOVComponent.class));
        this.dungeon = dungeon;
    }

    @Override
    protected void process(int entityId) {

    }
}
