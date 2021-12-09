package com.jscisco.lom.domain.entity;

import com.jscisco.lom.application.services.ZoneService;
import com.jscisco.lom.configuration.ApplicationConfiguration;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.action.DropItemAction;
import com.jscisco.lom.domain.action.PickUpItemAction;
import com.jscisco.lom.domain.item.Item;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.LevelGeneratorStrategy;
import com.jscisco.lom.domain.zone.Zone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class HeroIT {

    @Autowired
    ZoneService zoneService;

    private Zone zone;
    private Level level;

    @BeforeEach
    public void setup() {
        zone = zoneService.createZone();
        level = zoneService.createLevel(zone.getId(), 30, 30, LevelGeneratorStrategy.Strategy.EMPTY);
    }

    @Test
    public void can_save_level_with_hero_with_items_in_inventory() {
        // Given an hero with an item in its inventory
        Hero hero = EntityFactory.player();
        level.addEntityAtPosition(hero, Position.of(1, 1));
        Item item = new Item.Builder().withName(Name.of("Sword")).build();
        level.addItemAtPosition(item, Position.of(1, 1));
        PickUpItemAction action = new PickUpItemAction(hero, item);
        action.execute();

        assertThat(item).isIn(hero.getInventory().items);

        // When we save the level
        zoneService.saveLevel(level);

        // Then we hvae the item in our inventory on reload
        Level loadedLevel = zoneService.loadLevel(level.getId());
        hero = loadedLevel.getHero();
        assertThat(hero.getInventory().items.isEmpty()).isFalse();
    }

    @Test
    public void can_drop_item_after_loading() {
        // Given an hero with an item in its inventory
        Hero hero = EntityFactory.player();
        level.addEntityAtPosition(hero, Position.of(1, 1));
        Item item = new Item.Builder().withName(Name.of("Sword")).build();
        level.addItemAtPosition(item, Position.of(1, 1));
        PickUpItemAction action = new PickUpItemAction(hero, item);
        action.execute();
        assertThat(item).isIn(hero.getInventory().items);
        zoneService.saveLevel(level);

        Level loadedLevel = zoneService.loadLevel(level.getId());
        hero = loadedLevel.getHero();

        // When we drop the item
        DropItemAction dropItemAction = new DropItemAction(hero, hero.getInventory().getItems().get(0));
        dropItemAction.execute();

        // Then
        assertThat(loadedLevel.getItems().isEmpty()).isFalse();
    }

}
