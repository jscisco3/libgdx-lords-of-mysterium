package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.Direction;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.zone.Door;

public class WalkAction extends Action {
    Direction direction;

    public WalkAction(Entity source, Direction direction) {
        super(source);
        this.direction = direction;
    }

    @Override
    public ActionResult execute() {
        Position oldPosition = this.source.getPosition();
        Position newPosition = this.source.getPosition().add(direction.relativePosition);
        if (level.getTileAt(newPosition).isOccupied()) {
            return ActionResult.alternate(new AttackAction(source, level.getTileAt(newPosition).getOccupant()));
        }
        if (level.getTileAt(newPosition).getFeature() instanceof Door) {
            return ActionResult.alternate(new OpenDoorAction(source, level.getTileAt(newPosition)));
        }
        if (level.getTileAt(newPosition).isWalkable(source)) {
            this.source.move(newPosition);
            level.getTileAt(oldPosition).removeOccupant();
            level.getTileAt(newPosition).occupy(this.source);
            return ActionResult.succeeded();
        }
        return ActionResult.failed();
    }

    @Override
    public String toString() {
        return "WalkAction{" +
                "source=" + source +
                ", direction=" + direction +
                '}';
    }
}
