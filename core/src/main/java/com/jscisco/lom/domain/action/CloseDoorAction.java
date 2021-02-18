package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.zone.Door;

public class CloseDoorAction extends Action {

    private final Door door;

    public CloseDoorAction(Entity source, Door door) {
        super(source);
        this.door = door;
    }

    @Override
    public ActionResult execute() {
        // If it is already closed, do nothing
        if (!this.door.isOpen()) {
            return ActionResult.failed();
        }
        this.door.close();
        return ActionResult.succeeded();
    }
}
