package com.jscisco.lom.attributes.ai.btree.guards;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.jscisco.lom.entity.NPC;
import squidpony.squidmath.Coord;

import java.util.ArrayList;
import java.util.List;

public class HasPathGuard extends LeafTask<NPC> {

    @Override
    public Status execute() {
        NPC npc = getObject();
        List<Coord> path = (ArrayList<Coord>) npc.getKnowledge().get("PATH");
        return (path != null && !path.isEmpty()) ? Status.SUCCEEDED : Status.FAILED;
    }

    @Override
    protected Task<NPC> copyTo(Task<NPC> task) {
        return task;
    }
}
