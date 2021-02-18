package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.zone.Stage;

public abstract class Action {

    protected final Entity source;
    protected final Stage stage;

    public Action(Entity source) {
        this.source = source;
        this.stage = source.getStage();
    }

    public abstract ActionResult execute();

}
