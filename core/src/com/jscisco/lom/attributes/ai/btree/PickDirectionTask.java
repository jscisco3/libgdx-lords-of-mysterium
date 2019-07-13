package com.jscisco.lom.attributes.ai.btree;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.jscisco.lom.entity.NPC;
import com.jscisco.lom.util.Position;
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
        npc.learn("DIRECTION", position);
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<NPC> copyTo(Task<NPC> task) {
        return task;
    }
}
