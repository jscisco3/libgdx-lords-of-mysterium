package com.jscisco.lom.domain;

public abstract class Action {

    protected Actor source;
    protected Stage stage;

    public Action(Actor source, Stage stage) {
        this.source = source;
        this.stage = stage;
    }

    public abstract void execute();

}
