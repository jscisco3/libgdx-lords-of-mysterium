package com.jscisco.lom.action;

import com.jscisco.lom.actor.Entity;
import com.jscisco.lom.util.Position3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoveAction extends Action {

    private final static Logger logger = LoggerFactory.getLogger(MoveAction.class);
    Position3D direction;

    public MoveAction(Entity entity, int x, int y, int z) {
        super(entity);
        this.direction = new Position3D(x, y, z);
    }

    public MoveAction(Entity entity, Position3D direction) {
        super(entity);
        this.direction = direction;
    }

    @Override
    public ActionResult invoke() {
        Position3D oldPosition = source.getPosition();
        Position3D newPosition = oldPosition.add(direction);

        // Attack if something is there
        Entity e = source.getZone().getEntityAtPosition(newPosition);
        if (e != null) {
            logger.info("{} tried to move, but the space was occupied by {}.", source, e);
            return ActionResult.alternate(new AttackAction(source, e));
        }

        if (source.getZone().terrainIsWalkableAtPosition(newPosition)) {
            source.setPosition(newPosition);
            logger.debug("{} tried to move to {}, and succeeded!.", source, direction);
            return ActionResult.success();
        } else {
            logger.info("{} tried to move to {}, but failed.", source, direction);
            return ActionResult.alternate(new RestAction(source));
        }
    }
}
