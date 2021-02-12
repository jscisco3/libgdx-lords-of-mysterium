package com.jscisco.lom.domain;

public class WalkCommand extends Command {
    Direction direction;

    public WalkCommand(Actor target, Direction direction) {
        super(target);
        this.direction = direction;
    }

    @Override
    public void execute() {
        switch (direction) {
            case N:
                this.target.position = Position.of(this.target.position.getX(), this.target.position.getY() + 1);
                break;
            case S:
                this.target.position = Position.of(this.target.position.getX(), this.target.position.getY() - 1);
                break;
            default:
                break;
        }
    }
}
