package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.EntityFactory;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.item.Item;
import com.jscisco.lom.domain.item.ItemFactory;
import com.jscisco.lom.domain.zone.Level;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DropItemActionTest {

    // TODO: Fix
    Level level = new Level();
    Hero hero = EntityFactory.player();
    DropItemAction action;

    @Test
    public void givenIHaveNoItems_whenIDropAnItem_thenTheActionFails() {
        Item item = ItemFactory.sword();
        action = new DropItemAction(hero, item);
        ActionResult result = action.execute();

        assertThat(result.success()).isFalse();
    }

    @Test
    public void givenIHaveAtLeastOneItem_WhenIDropAnitem_thenItIsRemovedFromMyInventory_andAddedToTheTileIAmOn() {
        level.addEntityAtPosition(hero, Position.of(10, 10));

        Item item = ItemFactory.sword();
        hero.pickup(item);

        action = new DropItemAction(hero, item);
        action.execute();

        assertThat(hero.getInventory().getItems()).isEmpty();
        assertThat(level.getTileAt(Position.of(10, 10)).hasItems()).isTrue();
        assertThat(level.getTileAt(Position.of(10, 10)).getItems()).contains(item);
    }

}
