package com.jscisco.lom.attributes.ai.btree;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.jscisco.lom.action.MoveAction;
import com.jscisco.lom.entity.NPC;
import com.jscisco.lom.util.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WalkTask extends LeafTask<NPC> {

    private static final Logger logger = LoggerFactory.getLogger(WalkTask.class);


    @Override
    public void start() {
        super.start();
        logger.info("{} has started.", this);
    }

    @Override
    public Status execute() {
        NPC npc = getObject();
        Position direction = (Position) npc.getKnowledge().getOrDefault("DIRECTION_TO_WALK", null);
        if (direction == null) {
            return Status.FAILED;
        }
        npc.setNextAction(new MoveAction(npc, direction));
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<NPC> copyTo(Task<NPC> task) {
        return task;
    }
}
