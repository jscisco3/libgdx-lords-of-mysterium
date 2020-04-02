package com.jscisco.lom.domain;

import java.util.ArrayList;

public abstract class Action {

    Entity source;

    public Action(Entity source) {
        if (source == null) {
            throw new IllegalArgumentException("Source entity cannot be null!");
        }
        this.source = source;
    }

    public ActionResult invoke() {
        return ActionResult.success(new ArrayList<>());
    }

}
