package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.actor.Actor;
import com.jscisco.lom.domain.zone.Stage;

public abstract class Action {

    protected Actor source;
    protected Stage stage;

    public Action(Actor source, Stage stage) {
        this.source = source;
        this.stage = stage;
    }

    public abstract ActionResult execute();

}
