package com.jscisco.lom.attributes.ai;

import com.jscisco.lom.action.Action;
import com.jscisco.lom.action.MoveAction;
import com.jscisco.lom.action.RestAction;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.util.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidmath.Coord;

import java.util.ArrayList;
import java.util.List;

/**
 * This AI is responsible for tracking an Entity and attacking it.
 * Currently, it picks a target and then tries to "bump" attack it.
 * TODO: Ranged attacks
 * TODO: Skills
 */
public class HunterSeekerAI extends AbstractAI {

    private static final Logger logger = LoggerFactory.getLogger(HunterSeekerAI.class);

    private Entity target;

    public HunterSeekerAI(Entity entity) {
        super(entity);
        this.target = null;
    }

    public void findTarget() {
        this.target = this.entity.getStage().getPlayer();
    }

    @Override
    public Action nextAction() {
        if (target == null) {
            logger.info("{} has no target, so trying to find a target.", this.entity);
            findTarget();
            return new RestAction(this.entity);
        }

        Position direction = calculateDirectionToWalk();
        logger.debug("{} is hunting towards from {} towards {}.", this.entity, this.entity.getPosition(), this.target.getPosition());
        if (direction == null) {
            // If we can't path towards the target, let us rest.
            return new RestAction(this.entity);
        }
        return new MoveAction(this.entity, direction);
    }

    private Position calculateDirectionToWalk() {
        Coord entityCoord = Coord.get(this.entity.getX(), this.entity.getY());
        List<Coord> path = entity.getPathingMap().findPath(1,
                new ArrayList<Coord>(),
                new ArrayList<Coord>(),
                entityCoord,
                Coord.get(this.target.getX(), this.target.getY()));
        if (!path.isEmpty() && !path.get(0).equals(entityCoord)) {
            return new Position(path.get(0).x - entityCoord.x, path.get(0).y - entityCoord.y);
        } else {
            return null;
        }
    }
}
