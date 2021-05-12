package com.jscisco.lom.application.services;

import com.jscisco.lom.configuration.ApplicationConfiguration;
import com.jscisco.lom.configuration.PersistenceConfiguration;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.entity.EntityFactory;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.LevelGeneratorStrategy;
import com.jscisco.lom.domain.zone.LevelGeneratorStrategyFactory;
import com.jscisco.lom.domain.zone.Zone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;

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
        assertThatThrownBy(() -> zoneService.createLevel(12345L, 100, 100, new LevelGeneratorStrategy.EmptyLevelStrategy()))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void given_an_existing_zone_when_i_create_a_level_it_is_persisted_correctly() {
        // Given
        Zone createdZone = zoneService.createZone();
        // When
        Level createdLevel = zoneService.createLevel(createdZone.getId(), 100, 100, new LevelGeneratorStrategy.EmptyLevelStrategy());
        // Then
        assertThat(createdLevel.getId()).isEqualTo(1L);
    }

    @Test
    public void can_load_level_with_hero() {
        Zone zone = zoneService.createZone();

        Level level = zoneService.createLevel(zone.getId(), 25, 25, LevelGeneratorStrategyFactory.EMPTY);
        Hero hero = EntityFactory.player();
        entityService.createEntity(hero);
        level.addEntityAtPosition(hero, Position.of(2, 2));

        zoneService.saveLevel(level);

        level = zoneService.loadLevel(level.getId());

        assertThat(level.getHero()).isNotNull();
        assertThat(level.getTileAt(Position.of(2, 2)).isOccupied()).isTrue();
    }


    @Test
    public void loading_a_level_with_npcs_loads_the_correct_number_of_entities() {
        Zone zone = zoneService.createZone();

        Level level = zoneService.createLevel(zone.getId(), 25, 25, LevelGeneratorStrategyFactory.EMPTY);
        for (int i = 0; i < 5; i++) {
            Entity e = entityService.createEntity(EntityFactory.golem());
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

        Level level = zoneService.createLevel(zone.getId(), 20, 20, LevelGeneratorStrategyFactory.EMPTY);
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

        Level level = zoneService.createLevel(zone.getId(), 24, 24, LevelGeneratorStrategyFactory.EMPTY);
        for (int i = 0; i < 5; i++) {
            Entity e = entityService.createEntity(EntityFactory.golem());
            level.addEntityAtPosition(e, level.getEmptyTile(e));
        }
        level = zoneService.saveLevel(level);

        Entity testNpc = level.getEntities().get(0);

        assertThat(level.getEntities().size()).isEqualTo(5);
        level.removeEntity(testNpc);
//        entityService.deleteEntity(testNpc.getId());
        assertThat(level.getEntities().size()).isEqualTo(4);
        zoneService.saveLevel(level);

        level = zoneService.loadLevel(level.getId());
        assertThat(level.getEntities().size()).isEqualTo(4);

        zoneService.saveLevel(level);
    }
}
