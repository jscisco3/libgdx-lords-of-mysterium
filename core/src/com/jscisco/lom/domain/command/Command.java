package com.jscisco.lom.domain.command;

import com.jscisco.lom.domain.entity.GameObject;

public abstract class Command {

    protected GameObject entity;

    public Command(GameObject entity) {
        this.entity = entity;
    }

    public abstract void execute();

}
