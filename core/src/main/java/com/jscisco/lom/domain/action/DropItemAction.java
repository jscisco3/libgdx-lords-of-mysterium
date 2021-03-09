package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.entity.Entity;

public class DropItemAction extends Action {

    public DropItemAction(Entity source) {
        super(source);
    }

    @Override
    public ActionResult execute() {
        if (source.getInventory().getItems().isEmpty()) {
            return ActionResult.failed();
        }
        source.dropItem(source.getInventory().getItems().get(0));
        return ActionResult.succeeded();
    }
}
