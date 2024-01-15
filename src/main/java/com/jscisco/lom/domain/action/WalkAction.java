package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.Direction;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.zone.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class WalkAction extends Action {

    private static final Logger logger = LoggerFactory.getLogger(WalkAction.class);
    Direction direction;

    public WalkAction(Entity source, Direction direction) {
        super(source);
        this.direction = direction;
    }

    @Override
    public ActionResult execute() {
        Position newPosition = this.source.getPosition().add(direction.relativePosition);
        Optional<Entity> occupant = this.level.getEntities().stream().filter(e ->
                        e.getPosition().equals(newPosition))
                .findFirst();
        if (occupant.isPresent()) {
            return ActionResult.alternate(new AttackAction(source, occupant.get()));
        }
//        if (level.getTile(newPosition).getFeature() instanceof Door) {
//            return ActionResult.alternate(new OpenDoorAction(source, level.getTile(newPosition)));
//        }
        if (level.getTile(newPosition).isWalkable(source)) {
            // TODO: This seems bad. Should not be repeating all of this in _every_ function that moves a hero
            this.source.move(newPosition);
            return ActionResult.succeeded();
        }
        return ActionResult.failed();
    }

    @Override
    public String toString() {
        return "WalkAction{" + "source=" + source + ", direction=" + direction + '}';
    }
}
