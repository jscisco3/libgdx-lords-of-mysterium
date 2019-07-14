package com.jscisco.lom.action;

import com.jscisco.lom.entity.Entity;

public abstract class AbstractAction implements Action {
    protected final Entity source;

    public AbstractAction() {
        this.source = null;
    }

    public AbstractAction(Entity source) {
        this.source = source;
    }

    @Override
    public ActionResult invoke() {
        return ActionResult.success();
    }
}
