package com.jscisco.lom.attributes.ai.btree.guards;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.jscisco.lom.entity.NPC;
import com.jscisco.lom.util.Position;

public class HasTargetPositionGuard extends LeafTask<NPC> {

    @Override
    public Status execute() {
        Position target = (Position) getObject().getKnowledge().get("TARGET_POSITION");
        return (target != null) ? Status.SUCCEEDED : Status.FAILED;
    }

    @Override
    protected Task<NPC> copyTo(Task<NPC> task) {
        return task;
    }
}
