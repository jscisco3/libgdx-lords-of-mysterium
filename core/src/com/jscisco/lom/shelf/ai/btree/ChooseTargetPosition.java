package com.jscisco.lom.shelf.ai.btree;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.jscisco.lom.shelf.entity.NPC;
import com.jscisco.lom.shelf.domain.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ChooseTargetPosition extends LeafTask<NPC> {

    private static final Logger logger = LoggerFactory.getLogger(ChooseTargetPosition.class);

    @Override
    public void start() {
        super.start();
        logger.info("Starting: {}", this);
    }

    @Override
    public Status execute() {
        logger.debug("{} is running", this);
        NPC npc = getObject();
        Optional<Position> target = npc.getStage().findEmptyPosition();
        if (target == null) {
            logger.info("{} failed!.", this);
            return Status.FAILED;
        }
        npc.learn("TARGET_POSITION", target);
//        npc.learn("TARGET_POSITION", new Position(1, 2));
        logger.info("{} is finished.", this);
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<NPC> copyTo(Task<NPC> task) {
        return task;
    }
}
