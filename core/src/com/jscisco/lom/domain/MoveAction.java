package com.jscisco.lom.domain;

import com.jscisco.lom.application.EventProcessor;

public class MoveAction extends Action {

    Position newPosition;

    public MoveAction(EventProcessor processor, Entity source, Position newPosition) {
        super(processor, source);
        if (newPosition == null) {
            throw new IllegalArgumentException();
        }
        this.newPosition = newPosition;
    }

    @Override
    public ActionResult invoke() {
        this.eventProcessor.enqueue(new EntityMovementEvent(this.source, this.newPosition));
        return ActionResult.success();
    }
}
