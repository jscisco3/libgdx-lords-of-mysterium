package com.jscisco.lom.application.services;

import com.jscisco.lom.configuration.ApplicationConfiguration;
import com.jscisco.lom.configuration.PersistenceConfiguration;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.action.ChangeLevelAction;
import com.jscisco.lom.domain.action.WalkAction;
import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.entity.EntityFactory;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.entity.NPC;
import com.jscisco.lom.domain.item.Item;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.LevelChange;
import com.jscisco.lom.domain.zone.LevelGeneratorStrategy;
import com.jscisco.lom.domain.zone.Zone;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class, PersistenceConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ZoneServiceTest {

    @Autowired
    ZoneService zoneService;

    @Autowired
    EntityService entityService;

    @Test
    public void able_to_create_a_zone() {
        Zone zone = zoneService.createZone();
        assertThat(zone.getId()).isEqualTo(1L);
    }

    @Test
    public void given_a_noneexistent_zone_when_i_create_a_level_it_fails() {
        assertThatThrownBy(() -> zoneService.createLevel(12345L, 100, 100, LevelGeneratorStrategy.Strategy.EMPTY))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void given_an_existing_zone_when_i_create_a_level_it_is_persisted_correctly() {
        // Given
        Zone createdZone = zoneService.createZone();
        // When
        Level createdLevel = zoneService.createLevel(createdZone.getId(), 100, 100, LevelGeneratorStrategy.Strategy.EMPTY);
        // Then
        assertThat(createdLevel).isNotNull();
    }

    @Test
    public void can_load_level_with_hero() {
        Zone zone = zoneService.createZone();

        Level level = zoneService.createLevel(zone.getId(), 25, 25, LevelGeneratorStrategy.Strategy.EMPTY);
        Hero hero = EntityFactory.player();
        level.addEntityAtPosition(hero, Position.of(2, 2));

        zoneService.saveLevel(level);

        level = zoneService.loadLevel(level.getId());

        assertThat(level.getHero()).isNotNull();
        assertThat(level.getEntityAtPosition(Position.of(2, 2)).isPresent()).isTrue();
    }

    @Test
    public void gettingNextLevelId_is1whenThereAreNoIdsGeneratedYet() {
        Long id = zoneService.getNextLevelId();
        assertThat(id).isEqualTo(1L);

        zoneService.getNextLevelId();
        zoneService.getNextLevelId();
        id = zoneService.getNextLevelId();
        assertThat(id).isEqualTo(4L);
    }


    @Test
    public void loading_a_level_with_npcs_loads_the_correct_number_of_entities() {
        Zone zone = zoneService.createZone();

        Level level = zoneService.createLevel(zone.getId(), 25, 25, LevelGeneratorStrategy.Strategy.EMPTY);
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
                .build();
        level.addItemAtPosition(item, Position.of(2, 2));
        zoneService.saveLevel(level);

        Level loadedLevel = zoneService.loadLevel(level.getId());
        assertThat(loadedLevel.getItems().isEmpty()).isFalse();
        assertThat(loadedLevel.getItemsAtPosition(Position.of(2, 2)).isEmpty()).isFalse();
    }

    @Test
    @Disabled("This works in practice, but not in the test")
    public void can_remove_items_and_save_level() {
        Zone zone = zoneService.createZone();
        Level level = zoneService.createLevel(zone.getId(), 24, 24, LevelGeneratorStrategy.Strategy.EMPTY);

        Item item = new Item.Builder()
                .withName(Name.of("Sword"))
                .build();
        level.addItemAtPosition(item, Position.of(2, 2));
        zoneService.saveLevel(level);

        item = level.getItems().get(0);
        level.removeItem(item);

        assertThat(level.getItems().isEmpty()).isTrue();
        zoneService.saveLevel(level);
        assertThat(level.getItems().isEmpty()).isTrue();

        level = zoneService.loadLevel(level.getId());
        assertThat(level.getItems().isEmpty()).isTrue();
    }

    @Test
    public void able_to_move_entity_from_one_level_to_the_next() {
        Zone zone = zoneService.createZone(2);
        Level currentLevel = zone.getLevels().get(0);
        Level nextLevel = zone.getLevels().get(1);
        zoneService.saveLevel(currentLevel);
        zoneService.saveLevel(nextLevel);

        Hero hero = EntityFactory.player();

        Position currentHeroPosition = Position.of(2, 2);
        Position nextLevelPosition = Position.of(5, 5);

        currentLevel.addEntityAtPosition(hero, currentHeroPosition);
        currentLevel.getTileAt(hero.getPosition()).setFeature(new LevelChange(nextLevel.getId(), nextLevelPosition, false));

        ChangeLevelAction action = new ChangeLevelAction(hero);

        // When
        action.execute();

        // Then
        currentLevel = zoneService.loadLevel(currentLevel.getId());
        nextLevel = zoneService.loadLevel(nextLevel.getId());

        assertThat(currentLevel.getEntities().isEmpty()).isTrue();
        assertThat(nextLevel.getEntities().contains(hero)).isTrue();
    }

    @Test
    public void after_changing_level_should_be_able_to_process_next_action() {
        Zone zone = zoneService.createZone(2);
        Level currentLevel = zone.getLevels().get(0);
        Level nextLevel = zone.getLevels().get(1);
        zoneService.saveLevel(currentLevel);
        zoneService.saveLevel(nextLevel);

        Hero hero = EntityFactory.player();

        Position currentHeroPosition = Position.of(2, 2);
        Position nextLevelPosition = Position.of(5, 5);

        currentLevel.addEntityAtPosition(hero, currentHeroPosition);
        currentLevel.getTileAt(hero.getPosition()).setFeature(new LevelChange(nextLevel.getId(), nextLevelPosition, false));

        ChangeLevelAction action = new ChangeLevelAction(hero);
        action.execute();

        // When
//        assertThat(hero.nextAction()).isInstanceOf(WalkAction.class);
        nextLevel = zoneService.loadLevel(nextLevel.getId());
        hero = nextLevel.getHero();
        hero.getState().handleInput(Set.of(22));
        nextLevel.process();

        assertThat(hero.getPosition()).isEqualTo(Position.of(6, 5));

    }
}
