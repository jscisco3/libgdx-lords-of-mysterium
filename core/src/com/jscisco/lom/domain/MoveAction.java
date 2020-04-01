package com.jscisco.lom.domain;

public class MoveAction extends Action {

    Position newPosition;

    public MoveAction(Entity source, Position newPosition) {
        super(source);
        if (newPosition == null) {
            throw new IllegalArgumentException();
        }
        this.newPosition = newPosition;
    }

    @Override
    public ActionResult invoke() {
        this.source.move(newPosition);
        return ActionResult.success();
    }
}
