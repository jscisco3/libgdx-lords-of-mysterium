package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DropItemAction extends Action {
    final Logger logger = LoggerFactory.getLogger(DropItemAction.class);

    private final Item item;

    public DropItemAction(Entity source, Item item) {
        super(source);
        this.item = item;
    }

    @Override
    public ActionResult execute() {
        logger.debug("DropItemAction executed...");
        if (!source.getInventory().getItems().contains(item)) {
            return ActionResult.failed();
        }
        source.dropItem(item);
        level.addItem(item, source.getPosition());
        return ActionResult.succeeded();
    }
}
