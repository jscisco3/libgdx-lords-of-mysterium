package com.jscisco.lom.attributes.ai.btree;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.jscisco.lom.action.MoveAction;
import com.jscisco.lom.entity.NPC;
import com.jscisco.lom.domain.Position;
import squidpony.squidmath.Coord;

import java.util.ArrayList;
import java.util.List;

public class TakeStep extends LeafTask<NPC> {

    @Override
    public Status execute() {
        NPC npc = getObject();
        List<Coord> path = (ArrayList<Coord>) npc.getKnowledge().get("PATH");
        // Calculate direction to walk
        Position direction = new Position(path.get(0).x - npc.getX(), path.get(0).y - npc.getY());
        npc.setNextAction(new MoveAction(npc, direction));
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<NPC> copyTo(Task<NPC> task) {
        return task;
    }
}
