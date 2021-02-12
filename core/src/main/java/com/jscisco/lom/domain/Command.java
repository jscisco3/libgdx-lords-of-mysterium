package com.jscisco.lom.domain;

public abstract class Command {

    protected Actor target;

    public Command(Actor target) {
        this.target = target;
    }

    public abstract void execute();

}
