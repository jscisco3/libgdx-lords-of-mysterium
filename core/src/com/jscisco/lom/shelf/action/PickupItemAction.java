package com.jscisco.lom.shelf.action;

import com.jscisco.lom.shelf.entity.Entity;
import com.jscisco.lom.shelf.items.Item;

public class PickupItemAction extends AbstractAction {

    private Item item;

    public PickupItemAction(Entity source, Item item) {
        super(source);
        this.item = item;
    }

    @Override
    public ActionResult invoke() {
        this.source.getInventory().addItem(item);
        this.source.getStage().removeItem(item);
        this.item.setPosition(null);
        return ActionResult.success();
    }
}
