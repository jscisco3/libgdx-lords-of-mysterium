package com.jscisco.lom.domain;

public class WalkAction extends Action {
    Direction direction;

    public WalkAction(Actor source, Stage stage, Direction direction) {
        super(source, stage);
        this.direction = direction;
    }

    @Override
    public void execute() {
        Position newPosition = this.source.position.add(direction.relativePosition);
        if (stage.getTileAt(newPosition).isWalkable(source)) {
            this.source.position = newPosition;
        }
    }
}
