package com.jscisco.lom.shelf.ai.btree;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.jscisco.lom.shelf.entity.Entity;
import com.jscisco.lom.shelf.entity.NPC;

public class GetTargetEntity extends LeafTask<NPC> {

    @Override
    public Status execute() {
        NPC npc = getObject();
        Entity target = npc.getStage().getPlayer();

        npc.learn("TARGET_ENTITY", target);

        return Status.SUCCEEDED;
    }

    @Override
    protected Task<NPC> copyTo(Task<NPC> task) {
        return null;
    }
}
