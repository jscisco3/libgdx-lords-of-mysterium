package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.EntityFactory;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.item.Item;
import com.jscisco.lom.domain.item.ItemFactory;
import com.jscisco.lom.domain.zone.Level;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PickUpItemActionTest {

    // TODO: Fix
    Level level = new Level();
    Hero hero = EntityFactory.player();
    PickUpItemAction action;

    @Test
    public void givenITryToPickUpItem_whenThereAreNoItems_thenTheActionFails() {
        level.addEntityAtPosition(hero, Position.of(1, 1));

        action = new PickUpItemAction(hero);
        ActionResult result = action.execute();

        assertThat(result.success()).isFalse();
    }

    @Test
    public void givenITryToPickUpItem_whereThereIsAnItem_thenItSucceeds_andTheItemIsAddedToTheInventory_andRemovedFromTheTile() {
        level.addEntityAtPosition(hero, Position.of(1, 1));

        Item item = ItemFactory.sword();
        level.addItemAtPosition(item, hero.getPosition());

        action = new PickUpItemAction(hero);
        ActionResult result = action.execute();

        assertThat(result.success()).isTrue();
        assertThat(level.getTileAt(hero.getPosition()).hasItems()).isFalse();
        assertThat(hero.getInventory().getItems().isEmpty()).isFalse();
        assertThat(hero.getInventory().getItems()).contains(item);
    }

}
