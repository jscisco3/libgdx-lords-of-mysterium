package com.jscisco.lom.attributes.ai.btree;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.jscisco.lom.entity.NPC;
import com.jscisco.lom.util.Position;
import squidpony.squidmath.Coord;

import java.util.ArrayList;
import java.util.List;

public class CalculatePath extends LeafTask<NPC> {

    @Override
    public Status execute() {
        NPC npc = getObject();
        Position target = (Position) npc.getKnowledge().get("TARGET_POSITION");
        List<Coord> path = calculatePathToTarget(target);
        if (path == null) {
            return Status.FAILED;
        }
        npc.learn("PATH", path);
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<NPC> copyTo(Task<NPC> task) {
        return task;
    }

    private List<Coord> calculatePathToTarget(Position target) {
        NPC npc = getObject();
        Coord entityCoord = Coord.get(npc.getX(), npc.getY());
        List<Coord> path = npc.getPathingMap().findPath(1,
                new ArrayList<>(),
                new ArrayList<>(),
                entityCoord,
                Coord.get(target.getX(), target.getY()));
        if (!path.isEmpty()) {
            return path;
        }
        return null;
    }

}
