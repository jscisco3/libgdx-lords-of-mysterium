package com.jscisco.lom.attributes.ai.goap.actions;

import com.jscisco.lom.action.MoveAction;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.util.Position;
import squidpony.squidmath.Coord;

import java.util.ArrayList;
import java.util.List;

public class MoveToAction extends GOAPAction {

    public MoveToAction() {
        addPrecondition(GOAPGoal.HAS_TARGET, true);
//        addEffect(GOAPGoal.IN_RANGE, true);
    }

    @Override
    public void reset() {

    }

    @Override
    public boolean checkProceduralPreconditions(Entity entity) {
        return this.target != null;
    }

    @Override
    public boolean requiresInRange() {
        return false;
    }

    @Override
    public boolean perform(Entity entity) {
        if (entity.getPosition() != this.target) {
            Position direction = getDirectionForMove(entity);
            if (direction != null) {
                entity.setNextAction(new MoveAction(entity, direction));
                return true;
            }
        }
        this.finish();
        return true;
    }

    private Position getDirectionForMove(Entity entity) {
        Coord entityCoord = Coord.get(entity.getX(), entity.getY());
        List<Coord> path = entity.getPathingMap().findPath(1,
                new ArrayList<Coord>(),
                new ArrayList<Coord>(),
                entityCoord,
                Coord.get(this.target.getX(), this.target.getY()));
        if (!path.isEmpty() && !path.get(0).equals(entityCoord)) {
            return new Position(path.get(0).x - entityCoord.x, path.get(0).y - entityCoord.y);
        }
        return null;
    }
}
