package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.actor.Actor;
import com.jscisco.lom.domain.zone.Door;
import com.jscisco.lom.domain.zone.Stage;

public class OpenDoorAction extends Action {

    private final Door door;

    public OpenDoorAction(Actor source, Stage stage, Door door) {
        super(source, stage);
        this.door = door;
    }

    @Override
    public ActionResult execute() {
        // If it is already open, do nothing
        if (this.door.isOpen()) {
            return ActionResult.failed();
        }
        // Otherwise, open it.
        this.door.open();
        return ActionResult.succeeded();
    }
}
