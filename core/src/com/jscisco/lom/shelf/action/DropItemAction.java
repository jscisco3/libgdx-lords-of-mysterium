package com.jscisco.lom.shelf.action;

import com.jscisco.lom.shelf.entity.Entity;
import com.jscisco.lom.shelf.items.Item;

public class DropItemAction extends AbstractAction {

    private Item item;

    public DropItemAction(Entity source, Item item) {
        super(source);
        this.item = item;
    }

    @Override
    public ActionResult invoke() {
        if (this.source.drop(item)) {
            return ActionResult.success();
        }
        return ActionResult.failure();
    }
}
