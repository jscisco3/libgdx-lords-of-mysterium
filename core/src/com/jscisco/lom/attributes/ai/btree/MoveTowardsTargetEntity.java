package com.jscisco.lom.attributes.ai.btree;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.entity.NPC;

public class MoveTowardsTargetEntity extends LeafTask<NPC> {

    Entity target;

    @Override
    public void start() {
        super.start();
        target = (Entity) getObject().getKnowledge().get("TARGET_ENTITY");
    }

    @Override
    public Status execute() {
        return null;
    }

    @Override
    protected Task<NPC> copyTo(Task<NPC> task) {
        return task;
    }
}
