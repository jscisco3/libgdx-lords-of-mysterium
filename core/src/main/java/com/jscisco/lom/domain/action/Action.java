package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.zone.Stage;

public abstract class Action {

    protected Entity source;
    protected Stage stage;

    public Action(Entity source, Stage stage) {
        this.source = source;
        this.stage = stage;
    }

    public abstract ActionResult execute();

}
