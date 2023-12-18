package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.zone.Door;
import com.jscisco.lom.domain.zone.Tile;

public class OpenDoorAction extends Action {

    private final Tile tile;

    public OpenDoorAction(Entity source, Tile tile) {
        super(source);
        this.tile = tile;
    }

    @Override
    public ActionResult execute() {
        if (this.tile.getFeature() instanceof Door) {
            Door door = (Door) this.tile.getFeature();
            if (door.isOpen()) {
                return ActionResult.failed();
            }
            door.open();
            return ActionResult.succeeded();
        }
        return ActionResult.failed();
    }
}
