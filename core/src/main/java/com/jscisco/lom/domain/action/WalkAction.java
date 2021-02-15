package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.Direction;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.zone.Door;
import com.jscisco.lom.domain.zone.Stage;

public class WalkAction extends Action {
    Direction direction;

    public WalkAction(Entity source, Stage stage, Direction direction) {
        super(source, stage);
        this.direction = direction;
    }

    @Override
    public ActionResult execute() {
        Position oldPosition = this.source.getPosition();
        Position newPosition = this.source.getPosition().add(direction.relativePosition);
        if (stage.getTileAt(newPosition).getFeature() instanceof Door) {
            return ActionResult.alternate(new OpenDoorAction(source, stage, stage.getTileAt(newPosition)));
        }
        if (stage.getTileAt(newPosition).isWalkable(source)) {
            this.source.move(newPosition);
            stage.getTileAt(oldPosition).occupy(null);
            stage.getTileAt(newPosition).occupy(this.source);
            return ActionResult.succeeded();
        }
        return ActionResult.failed();
    }
}
