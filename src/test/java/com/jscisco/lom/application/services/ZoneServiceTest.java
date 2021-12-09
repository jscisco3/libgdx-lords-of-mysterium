package com.jscisco.lom.application.services;

import com.jscisco.lom.configuration.ApplicationConfiguration;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.action.ChangeLevelAction;
import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.entity.EntityFactory;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.entity.NPC;
import com.jscisco.lom.domain.item.Item;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.LevelChange;
import com.jscisco.lom.domain.zone.LevelGeneratorStrategy;
import com.jscisco.lom.domain.zone.Zone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
public class ZoneServiceTest {

    @Autowired
    ZoneService zoneService;

    @Test
    public void able_to_create_a_zone() {
        Zone zone = zoneService.createZone();
        assertThat(zone).isNotNull();
    }

    @Test
    public void given_an_existing_zone_when_i_create_a_level_it_is_persisted_correctly() {
        // Given
        Zone createdZone = zoneService.createZone();
        // When
        Level createdLevel = zoneService.createLevel(createdZone, 100, 100, LevelGeneratorStrategy.Strategy.EMPTY);
        // Then
        assertThat(createdLevel).isNotNull();
    }

    @Test
    public void can_load_level_with_hero() {
        Zone zone = zoneService.createZone();

        Level level = zoneService.createLevel(zone, 25, 25, LevelGeneratorStrategy.Strategy.EMPTY);
        Hero hero = EntityFactory.player();
        level.addEntityAtPosition(hero, Position.of(2, 2));

        zoneService.saveZone(zone);

        level = zoneService.loadLevel(level.getId());

        assertThat(level.getHero()).isNotNull();
        assertThat(level.getEntityAtPosition(Position.of(2, 2)).isPresent()).isTrue();
    }

    @Test
    public void loading_a_level_with_npcs_loads_the_correct_number_of_entities() {
        Zone zone = zoneService.createZone();

        Level level = zoneService.createLevel(zone, 25, 25, LevelGeneratorStrategy.Strategy.EMPTY);
        for (int i = 0; i < 5; i++) {
            NPC e = EntityFactory.golem();
            level.addEntityAtPosition(e, level.getEmptyTile(e));
        }
        zoneService.saveLevel(level);

        assertThat(level.getEntities().size()).isEqualTo(5);

        Level loadedLevel = zoneService.loadLevel(level.getId());

        assertThat(loadedLevel.getEntities().size()).isEqualTo(5);
    }

    @Test
    public void can_remove_entity_from_level_correctly() {
        Zone zone = zoneService.createZone();

        Level level = zoneService.createLevel(zone.getId(), 20, 20, LevelGeneratorStrategy.Strategy.EMPTY);
        for (int i = 0; i < 5; i++) {
            Entity e = EntityFactory.golem();
            level.addEntityAtPosition(e, level.getEmptyTile(e));
        }
        Level savedLevel = zoneService.saveLevel(level);
        Entity testNpc = savedLevel.getEntities().get(0);

        assertThat(level.getEntities().size()).isEqualTo(5);
        savedLevel.removeEntity(testNpc);
        assertThat(savedLevel.getEntities().size()).isEqualTo(4);
        zoneService.saveLevel(savedLevel);

        Level loadedLevel = zoneService.loadLevel(level.getId());
        assertThat(loadedLevel.getEntities().size()).isEqualTo(4);
    }


    @Test
    public void can_load_a_game_with_a_removed_entity_and_then_save_it() {
        Zone zone = zoneService.createZone();

        Level level = zoneService.createLevel(zone.getId(), 24, 24, LevelGeneratorStrategy.Strategy.EMPTY);
        for (int i = 0; i < 5; i++) {
            NPC e = EntityFactory.golem();
            level.addEntityAtPosition(e, level.getEmptyTile(e));
        }
        level = zoneService.saveLevel(level);

        Entity testNpc = level.getEntities().get(0);

        assertThat(level.getEntities().size()).isEqualTo(5);
        level.removeEntity(testNpc);
        assertThat(level.getEntities().size()).isEqualTo(4);
        zoneService.saveLevel(level);

        level = zoneService.loadLevel(level.getId());
        assertThat(level.getEntities().size()).isEqualTo(4);

        zoneService.saveLevel(level);
    }

    @Test
    public void can_save_level_with_items() {
        Zone zone = zoneService.createZone();
        Level level = zoneService.createLevel(zone.getId(), 24, 24, LevelGeneratorStrategy.Strategy.EMPTY);

        Item item = new Item.Builder()
                .withName(Name.of("Sword"))
                .build();
        level.addItemAtPosition(item, Position.of(2, 2));

        level = zoneService.saveLevel(level);

        item = level.getItems().get(0);
        assertThat(item.getId()).isEqualTo(1L);
        assertThat(item.getName()).isEqualTo(Name.of("Sword"));
    }

    @Test
    public void loading_level_with_items_places_items_in_appropriate_tile() {
        Zone zone = zoneService.createZone();
        Level level = zoneService.createLevel(zone.getId(), 24, 24, LevelGeneratorStrategy.Strategy.EMPTY);

        Item item = new Item.Builder()
                .withName(Name.of("Sword"))
                .withGlyph("sword")
                .build();
        level.addItemAtPosition(item, Position.of(2, 2));
        zoneService.saveLevel(level);

        Level loadedLevel = zoneService.loadLevel(level.getId());
        assertThat(loadedLevel.getItems().isEmpty()).isFalse();
        assertThat(loadedLevel.getItemsAtPosition(Position.of(2, 2)).isEmpty()).isFalse();
    }

    @Test
    public void can_remove_items_and_save_level() {
        Zone zone = zoneService.createZone();
        Level level = zoneService.createLevel(zone, 24, 24, LevelGeneratorStrategy.Strategy.EMPTY);

        Item item = new Item.Builder()
                .withName(Name.of("Sword"))
                .build();
        level.addItemAtPosition(item, Position.of(2, 2));
        zoneService.saveZone(zone);

        item = level.getItems().get(0);
        level.removeItem(item);

        assertThat(level.getItems().isEmpty()).isTrue();
        zoneService.saveZone(zone);
        assertThat(level.getItems().isEmpty()).isTrue();

        zone = zoneService.getZone(zone.getId());
        level = zone.getLevelById(level.getId());
        assertThat(level.getItems().isEmpty()).isTrue();
    }

    @Test
    public void able_to_move_entity_from_one_level_to_the_next() {
        Zone zone = zoneService.createZone(2);
        Level currentLevel = zone.getLevels().get(0);
        Level nextLevel = zone.getLevels().get(1);
        zoneService.saveZone(zone);

        Hero hero = EntityFactory.player();

        Position currentHeroPosition = Position.of(2, 2);
        Position nextLevelPosition = Position.of(5, 5);

        currentLevel.addEntityAtPosition(hero, currentHeroPosition);
        currentLevel.getTileAt(hero.getPosition()).setFeature(new LevelChange(nextLevel.getId(), nextLevelPosition, false));

        ChangeLevelAction action = new ChangeLevelAction(hero);

        // When
        action.execute();

        assertThat(currentLevel.getEntities().isEmpty()).isTrue();
        assertThat(nextLevel.getEntities().contains(hero)).isTrue();
    }

    @Test
    public void after_changing_level_should_be_able_to_process_next_action() {
        Zone zone = zoneService.createZone(2);
        zoneService.saveZone(zone);
        Level currentLevel = zone.getLevels().get(0);
        Level nextLevel = zone.getLevels().get(1);

        Hero hero = EntityFactory.player();

        Position currentHeroPosition = Position.of(2, 2);
        Position nextLevelPosition = Position.of(5, 5);

        currentLevel.addEntityAtPosition(hero, currentHeroPosition);
        currentLevel.getTileAt(hero.getPosition()).setFeature(new LevelChange(nextLevel.getId(), nextLevelPosition, false));

        ChangeLevelAction action = new ChangeLevelAction(hero);
        action.execute();

        // When
        hero = nextLevel.getHero();
        hero.getState().handleInput(Set.of(22));
        nextLevel.process();

        assertThat(hero.getPosition()).isEqualTo(Position.of(6, 5));
    }

    @Test
    public void loading_a_zone_should_result_in_actual_object() {
        // Given a persisted zone
        Zone zone = zoneService.createZone(5);
        zone = zoneService.saveZone(zone);
        UUID zoneId = zone.getId();

        // When we load that zone
        Zone loadedZone = zoneService.getZone(zoneId);

        // Then it is the right class and everything is in memory
        assertThat(loadedZone).isInstanceOf(Zone.class);
    }

    @Test
    public void loading_a_zone_should_result_in_levels_being_actual_objects() {
        // Given a persisted zone
        Zone zone = zoneService.createZone(5);
        zone = zoneService.saveZone(zone);
        UUID zoneId = zone.getId();

        // When we load that zone and get a level
        Zone loadedZone = zoneService.getZone(zoneId);
        Level level = loadedZone.getLevels().get(0);

        // Then it is the right class and everything is in memory
        assertThat(loadedZone).isInstanceOf(Zone.class);
        assertThat(level).isInstanceOf(Level.class);
    }

    @Test
    public void saving_a_zone_with_a_level_with_entities_and_loading_them() {
        Zone zone = zoneService.createZone(1);
        zone = zoneService.saveZone(zone);
        UUID zoneId = zone.getId();

        // When we load that zone and get a level
        Zone loadedZone = zoneService.getZone(zoneId);
        Level level = loadedZone.getLevels().get(0);

        NPC golem = EntityFactory.golem();
        level.addEntityAtPosition(golem, Position.of(10, 10));


        zoneService.saveZone(loadedZone);

        // When we load the zone again and get the level

        loadedZone = zoneService.getZone(zoneId);

        level = loadedZone.getLevels().get(0);
        // Then we have that golem
        assertThat(level.getEntities().size()).isEqualTo(1);
    }

    @Test
    public void saving_a_zone_with_a_level_with_entities_and_loading_them_then_removing_entity_and_saving() {
        Zone zone = zoneService.createZone(1);
        zone = zoneService.saveZone(zone);
        UUID zoneId = zone.getId();

        // When we load that zone and get a level
        Zone loadedZone = zoneService.getZone(zoneId);
        Level level = loadedZone.getLevels().get(0);

        NPC golem = EntityFactory.golem();
        level.addEntityAtPosition(golem, Position.of(10, 10));

        zoneService.saveZone(loadedZone);

        // When we load the zone again and get the level

        loadedZone = zoneService.getZone(zoneId);

        level = loadedZone.getLevels().get(0);
        // Then we have that golem
        assertThat(level.getEntities().size()).isEqualTo(1);

        // Now, let us remove that entity
        golem = (NPC) level.getEntities().get(0);
        level.removeEntity(golem);

        zoneService.saveZone(loadedZone);

        loadedZone = zoneService.getZone(zoneId);
        level = loadedZone.getLevels().get(0);
        assertThat(level.getEntities().isEmpty()).isTrue();
    }
}
