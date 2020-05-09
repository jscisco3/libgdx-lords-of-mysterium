package com.jscisco.lom.shelf.ai.btree.guards;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.jscisco.lom.shelf.entity.Entity;
import com.jscisco.lom.shelf.entity.NPC;

public class HasTargetEntityGuard extends LeafTask<NPC> {

    @Override
    public Status execute() {
        NPC npc = getObject();
        Entity target = (Entity) npc.getKnowledge().getOrDefault("TARGET_ENTITY", null);
        return (target != null) ? Status.SUCCEEDED : Status.FAILED;
    }

    @Override
    protected Task<NPC> copyTo(Task<NPC> task) {
        return task;
    }
}
