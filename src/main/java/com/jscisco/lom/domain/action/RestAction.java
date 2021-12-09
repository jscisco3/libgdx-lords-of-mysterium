package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.entity.Entity;

public class RestAction extends Action {

    public RestAction(Entity source) {
        super(source);
    }

    @Override
    public ActionResult execute() {
        return ActionResult.succeeded();
    }
}
