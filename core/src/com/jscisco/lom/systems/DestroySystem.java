package com.jscisco.lom.systems;

import com.artemis.Aspect;
import com.artemis.systems.IteratingSystem;
import com.jscisco.lom.components.flags.Destroy;

public class DestroySystem extends IteratingSystem {

    public DestroySystem() {
        super(Aspect.all(Destroy.class));
    }

    @Override
    protected void process(int entityId) {
        world.delete(entityId);
    }
}
