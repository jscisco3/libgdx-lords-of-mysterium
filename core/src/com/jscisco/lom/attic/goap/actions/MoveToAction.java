package com.jscisco.lom.attic.goap.actions;

import com.jscisco.lom.action.MoveAction;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.util.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidmath.Coord;

import java.util.ArrayList;
import java.util.List;

public class MoveToAction extends GOAPAction {

    private static final Logger logger = LoggerFactory.getLogger(MoveAction.class);

    public MoveToAction() {
        // We have a target
        addPrecondition(GOAPGoal.HAS_TARGET, true);
        // But we are not in position
        addPrecondition(GOAPGoal.IN_POSITION, false);
        addEffect(GOAPGoal.IN_POSITION, true);
    }

    @Override
    public void reset() {

    }

    @Override
    public boolean checkProceduralPreconditions(Entity entity) {
        return true;
    }

    @Override
    public boolean perform(Entity entity) {
        if (entity.getPosition() != this.target) {
            // Determine in which direction we need to move
            Position direction = getDirectionForMove(entity);
            if (direction != null) {
                // Set that movement
                entity.setNextAction(new MoveAction(entity, direction));
                return true;
            }
        }
        // If we are there, then this action is complete.
        logger.info("We found the position");
        entity.updateWorldState(GOAPGoal.IN_POSITION, true);
        this.finish();
        return true;
    }

    private Position getDirectionForMove(Entity entity) {

        Position goal = (Position) entity.getWorldState().get(GOAPGoal.TARGET);
        logger.info("Goal: {}", goal);

        Coord entityCoord = Coord.get(entity.getX(), entity.getY());
        List<Coord> path = entity.getPathingMap().findPath(1,
                new ArrayList<>(),
                new ArrayList<>(),
                entityCoord,
                Coord.get(goal.getX(), goal.getY()));
        if (!path.isEmpty() && !path.get(0).equals(entityCoord)) {
            return new Position(path.get(0).x - entityCoord.x, path.get(0).y - entityCoord.y);
        }
        return null;
    }
}
