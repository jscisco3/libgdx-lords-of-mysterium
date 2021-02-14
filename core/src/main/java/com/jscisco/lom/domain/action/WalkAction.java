package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.Direction;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.actor.Actor;
import com.jscisco.lom.domain.zone.Door;
import com.jscisco.lom.domain.zone.Stage;

public class WalkAction extends Action {
    Direction direction;

    public WalkAction(Actor source, Stage stage, Direction direction) {
        super(source, stage);
        this.direction = direction;
    }

    @Override
    public ActionResult execute() {
        Position newPosition = this.source.getPosition().add(direction.relativePosition);
        if (stage.getTileAt(newPosition).getFeature() instanceof Door) {
            return ActionResult.alternate(new OpenDoorAction(source, stage, (Door) stage.getTileAt(newPosition).getFeature()));
        }
        if (stage.getTileAt(newPosition).isWalkable(source)) {
            this.source.move(newPosition);
            return ActionResult.succeeded();
        }
        return ActionResult.failed();
    }
}
