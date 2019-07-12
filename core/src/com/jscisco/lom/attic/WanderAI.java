package com.jscisco.lom.attic;

import com.jscisco.lom.action.Action;
import com.jscisco.lom.action.MoveAction;
import com.jscisco.lom.action.RestAction;
import com.jscisco.lom.attributes.ai.AbstractAI;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.util.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidmath.Coord;

import java.util.ArrayList;
import java.util.List;

/**
 * Choose a random, empty position on the map and then begin wandering towards it.
 * Once the goal is reached, or becomes unreachable - choose a new goal.
 */
public class WanderAI extends AbstractAI {

    private static final Logger logger = LoggerFactory.getLogger(WanderAI.class);

    private Position goal;

    public WanderAI(Entity entity) {
        super(entity);
        this.goal = entity.getStage().findEmptyPosition();
    }

    @Override
    public Action nextAction() {
        Position direction = calculateDirectionToWalk();
        logger.debug("{} is wandering from {} towards {}.", this.entity, this.entity.getPosition(), goal);
        if (direction == null) {
            logger.debug("But the calculated direction was null");
            return new RestAction(this.entity);
        }
        return new MoveAction(this.entity, direction);
    }

    private Position calculateDirectionToWalk() {
        Coord entityCoord = Coord.get(this.entity.getX(), this.entity.getY());
        List<Coord> path = entity.getPathingMap().findPath(1,
                new ArrayList<>(),
                new ArrayList<>(),
                entityCoord,
                Coord.get(this.goal.getX(), this.goal.getY()));
        if (!path.isEmpty() && !path.get(0).equals(entityCoord)) {
            return new Position(path.get(0).x - entityCoord.x, path.get(0).y - entityCoord.y);
        } else {
            // No path, so find a new goal
            this.goal = this.entity.getStage().findEmptyPosition();
            this.entity.getPathingMap().clearGoals();
            return null;
        }
    }

}
