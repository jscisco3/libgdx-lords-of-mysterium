package com.jscisco.lom.shelf.ai.btree;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.jscisco.lom.shelf.entity.NPC;
import com.jscisco.lom.shelf.domain.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PickDirectionTask extends LeafTask<NPC> {

    private static final Logger logger = LoggerFactory.getLogger(PickDirectionTask.class);

    @Override
    public void start() {
        super.start();
        logger.info("{} has started.", this);
    }

    @Override
    public Status execute() {
        NPC npc = getObject();
        Position position = new Position(1, 0);
        npc.learn("DIRECTION_TO_WALK", position);
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<NPC> copyTo(Task<NPC> task) {
        return task;
    }
}
