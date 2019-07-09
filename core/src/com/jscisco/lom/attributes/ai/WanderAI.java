package com.jscisco.lom.attributes.ai;

import com.jscisco.lom.action.Action;
import com.jscisco.lom.action.MoveAction;
import com.jscisco.lom.actor.Entity;
import com.jscisco.lom.util.Position3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidmath.RNG;

public class WanderAI implements AI {

    private static final Logger logger = LoggerFactory.getLogger(WanderAI.class);

    RNG rng = new RNG();

    @Override
    public Action nextAction(Entity entity) {
        Position3D direction = new Position3D(rng.between(-1, 2), rng.between(-1, 2), 0);
        logger.debug("{} is wandering towards {}.", entity, direction);
        return new MoveAction(entity, direction);
    }
}
