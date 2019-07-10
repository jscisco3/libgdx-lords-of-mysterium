package com.jscisco.lom.attributes.ai;

import com.jscisco.lom.action.Action;
import com.jscisco.lom.action.MoveAction;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.util.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidmath.RNG;

public class WanderAI implements AI {

    private static final Logger logger = LoggerFactory.getLogger(WanderAI.class);

    RNG rng = new RNG();

    @Override
    public Action nextAction(Entity entity) {
        Position direction = new Position(rng.between(-1, 2), rng.between(-1, 2));
        logger.debug("{} is wandering towards {}.", entity, direction);
        return new MoveAction(entity, direction);
    }
}
