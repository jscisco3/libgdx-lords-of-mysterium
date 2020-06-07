package com.jscisco.lom.domain;

public class MoveAction extends Action {
    final GameObject source;
    final Position newPosition;

    public MoveAction(GameObject source, Position newPosition) {
        this.source = source;
        this.newPosition = newPosition;
    }

    @Override
    ActionResult invoke() {
        this.source.moveTo(this.newPosition);
        return ActionResult.success();
    }
}
