package com.jscisco.lom.action;

import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.items.Item;

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
