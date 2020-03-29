package com.jscisco.lom.action;

import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.domain.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Deprecated
public class MoveAction extends AbstractAction {

    private final static Logger logger = LoggerFactory.getLogger(MoveAction.class);
    Position direction;

    public MoveAction(Entity entity, int x, int y) {
        super(entity);
        this.direction = new Position(x, y);
    }

    public MoveAction(Entity entity, Position direction) {
        super(entity);
        this.direction = direction;
    }

    @Override
    public ActionResult invoke() {
        Position oldPosition = source.getPosition();
        Position newPosition = oldPosition.add(direction);

        // Attack if something is there
        Entity e = source.getStage().getEntityAtPosition(newPosition);
        if (e != null) {
            logger.info("{} tried to move, but the space was occupied by {}.", source, e);
            return ActionResult.alternate(new AttackAction(source, e));
        }

        if (source.getStage().terrainIsWalkableAtPosition(newPosition)) {
            source.setPosition(newPosition);
            logger.debug("{} tried to move to {}, and succeeded!.", source, direction);
            return ActionResult.success();
        } else {
            logger.debug("{} tried to move to {}, but failed.", source, direction);
            return ActionResult.alternate(new RestAction(source));
        }
    }
}
