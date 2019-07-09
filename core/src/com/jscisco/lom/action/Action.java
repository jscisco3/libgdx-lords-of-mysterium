package com.jscisco.lom.action;

import com.jscisco.lom.actor.Entity;

public abstract class Action {

    protected Entity source;

    public Action() {
        this.source = null;
    }

    public Action(Entity source) {
        this.source = source;
    }

    public ActionResult invoke() {
        return ActionResult.success();
    }
}
