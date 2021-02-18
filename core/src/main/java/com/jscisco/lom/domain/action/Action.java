package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.zone.Level;

public abstract class Action {

    protected final Entity source;
    protected final Level level;

    public Action(Entity source) {
        this.source = source;
        this.level = source.getStage();
    }

    public abstract ActionResult execute();

}
