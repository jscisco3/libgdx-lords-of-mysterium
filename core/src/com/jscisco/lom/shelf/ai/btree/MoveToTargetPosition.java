package com.jscisco.lom.shelf.ai.btree;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.jscisco.lom.shelf.action.MoveAction;
import com.jscisco.lom.shelf.entity.NPC;
import com.jscisco.lom.shelf.domain.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidmath.Coord;

import java.util.ArrayList;
import java.util.List;

public class MoveToTargetPosition extends LeafTask<NPC> {

    private static final Logger logger = LoggerFactory.getLogger(MoveToTargetPosition.class);

    private Position target;

    @Override
    public void start() {
        super.start();
        logger.info("{} has started", this);
        this.target = (Position) getObject().getKnowledge().get("TARGET_POSITION");
    }

    @Override
    public Status execute() {
        logger.debug("{} is running", this);
        NPC npc = getObject();
        if (npc.getPosition().equals(this.target)) {
            // We no longer have a target position
            npc.learn("TARGET_POSITION", null);
            logger.info("{} is finished.", this);
            return Status.SUCCEEDED;
        } else {
            Position direction = calculateDirectionToWalk();
            if (direction == null) {
                return Status.FAILED;
            }
            npc.setNextAction(new MoveAction(npc, direction));
        }
        return Status.RUNNING;
    }

    @Override
    protected Task<NPC> copyTo(Task<NPC> task) {
        return task;
    }

    @Override
    public void reset() {
        super.reset();
        this.target = null;
    }

    private Position calculateDirectionToWalk() {
        NPC npc = getObject();
        Coord entityCoord = Coord.get(npc.getX(), npc.getY());
        List<Coord> path = npc.getPathingMap().findPath(1,
                new ArrayList<>(),
                new ArrayList<>(),
                entityCoord,
                Coord.get(this.target.x(), this.target.y()));
        if (!path.isEmpty() && !path.get(0).equals(entityCoord)) {
            return new Position(path.get(0).x - entityCoord.x, path.get(0).y - entityCoord.y);
        } else {
            // No path, so return a null direction.
            return null;
        }
    }

}
