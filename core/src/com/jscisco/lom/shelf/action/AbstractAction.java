package com.jscisco.lom.shelf.action;

import com.jscisco.lom.shelf.entity.Entity;

@Deprecated
public abstract class AbstractAction implements Action {
    protected final Entity source;

    public AbstractAction(Entity source) {
        this.source = source;
    }

    @Override
    public ActionResult invoke() {
        return ActionResult.success();
    }
}
