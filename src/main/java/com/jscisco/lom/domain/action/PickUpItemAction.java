package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.item.Item;

import java.util.stream.Collectors;

/**
 * Pick up the top item in the tile that the player is in.
 * <p>
 * TODO: Refactor to pick up a particular item, which removes it from the correct tile
 */
public class PickUpItemAction extends Action {

    private final Item item;

    public PickUpItemAction(Entity source, Item item) {
        super(source);
        this.item = item;
    }

    @Override
    public ActionResult execute() {
         if (level.getItems().stream().filter(i -> {
             assert i.getPosition() != null;
             return i.getPosition().equals(source.getPosition());
         }).toList().contains(item) && item != null) {
             source.pickup(item);
             source.getLevel().removeItem(item);
             return ActionResult.succeeded();
         }
        return ActionResult.failed();
    }
}
