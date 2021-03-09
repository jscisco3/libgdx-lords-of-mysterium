package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.item.Item;
import com.jscisco.lom.domain.zone.Tile;

/**
 * Pick up the top item in the tile that the player is in.
 * <p>
 * TODO: Refactor to pick up a particular item, which removes it from the correct tile
 */
public class PickUpItemAction extends Action {

    public PickUpItemAction(Entity source) {
        super(source);
    }

    @Override
    public ActionResult execute() {
        Tile tile = source.getLevel().getTileOccupiedByEntity(source);
        Item item = tile.getTopItem();
        if (item != null) {
            source.pickup(item);
            tile.removeItem(item);
            return ActionResult.succeeded();
        }
        return ActionResult.failed();
    }
}
