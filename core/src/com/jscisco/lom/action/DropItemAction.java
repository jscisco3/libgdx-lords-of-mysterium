package com.jscisco.lom.action;

import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.items.Item;

public class DropItemAction extends AbstractAction {

    private Item item;

    public DropItemAction(Entity source, Item item) {
        super(source);
        this.item = item;
    }

    @Override
    public ActionResult invoke() {
        this.source.getInventory().removeItem(item);
        item.setPosition(this.source.getPosition());
        this.source.getStage().addItem(item);
        return ActionResult.success();
    }
}
