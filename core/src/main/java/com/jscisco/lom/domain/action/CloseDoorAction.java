package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.zone.Door;
import com.jscisco.lom.domain.zone.Stage;

public class CloseDoorAction extends Action {

    private final Door door;

    public CloseDoorAction(Entity source, Stage stage, Door door) {
        super(source, stage);
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
