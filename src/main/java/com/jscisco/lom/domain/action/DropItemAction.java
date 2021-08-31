package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.item.Item;

public class DropItemAction extends Action {

    private final Item item;

    public DropItemAction(Entity source, Item item) {
        super(source);
        this.item = item;
    }

    @Override
    public ActionResult execute() {
        if (!source.getInventory().getItems().contains(item)) {
            return ActionResult.failed();
        }
        source.dropItem(item);
        return ActionResult.succeeded();
    }
}
