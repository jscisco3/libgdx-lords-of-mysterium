package com.jscisco.lom.application;

import com.badlogic.gdx.Screen;
import com.jscisco.lom.Game;
import com.jscisco.lom.configuration.ApplicationConfiguration;
import com.jscisco.lom.configuration.PersistenceConfiguration;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.SaveGame;
import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.entity.EntityFactory;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.entity.NPC;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.Zone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class, PersistenceConfiguration.class})
public class GameServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(GameServiceTest.class);

    @Autowired
    private GameService gameService;

    @Test
    public void can_create_save_game() {
        gameService.saveGame(new SaveGame());
    }

    @Test
    public void can_save_level() {
        SaveGame game = new SaveGame();
        Zone zone = new Zone(1);
        game.addZone(zone);
        Level level = zone.getLevels().get(0);
        Hero hero = EntityFactory.player();
        level.addEntityAtPosition(hero, Position.of(1, 1));
        gameService.saveGame(game);

        game.setLevelId(level.getId());
        gameService.saveGame(game);

        assertThat(level.getId()).isNotNull();
        assertThat(level.getId()).isEqualTo(1L);
    }

    @Test
    public void can_load_level_with_hero() {
        SaveGame game = new SaveGame();
        Zone zone = new Zone(1);
        game.addZone(zone);

        Level level = zone.getLevels().get(0);
        Hero hero = EntityFactory.player();
        level.addEntityAtPosition(hero, Position.of(2, 2));

        gameService.saveGame(game);
        game.setLevelId(level.getId());
        gameService.saveGame(game);

        level = gameService.loadLevel(level.getId());

        assertThat(level.getHero()).isNotNull();
        assertThat(level.getTileAt(Position.of(2, 2)).isOccupied()).isTrue();
    }

    @Test
    public void loading_a_level_with_npcs_loads_the_correct_number_of_entities() {
        SaveGame game = new SaveGame();
        Zone zone = new Zone(1);
        game.addZone(zone);

        Level level = zone.getLevels().get(0);
        for (int i = 0; i < 5; i++) {
            NPC npc = EntityFactory.golem();
            level.addEntityAtPosition(npc, level.getEmptyTile(npc));
        }
        gameService.saveGame(game);

        assertThat(level.getEntities().size()).isEqualTo(5);

        Level loadedLevel = gameService.loadLevel(level.getId());

        assertThat(loadedLevel.getEntities().size()).isEqualTo(5);
    }

    @Test
    public void can_remove_entity_from_level_correctly() {
        SaveGame game = new SaveGame();
        Zone zone = new Zone(1);
        game.addZone(zone);

        Level level = zone.getLevels().get(0);
        for (int i = 0; i < 5; i++) {
            NPC npc = EntityFactory.golem();
            level.addEntityAtPosition(npc, level.getEmptyTile(npc));
        }
        gameService.saveGame(game);

        Entity testNpc = level.getEntities().get(0);

        assertThat(level.getEntities().size()).isEqualTo(5);
        level.removeEntity(testNpc);
        assertThat(level.getEntities().size()).isEqualTo(4);
        gameService.saveGame(game);

        Level loadedLevel = gameService.loadLevel(level.getId());
        assertThat(loadedLevel.getEntities().size()).isEqualTo(4);
    }

    @Test
    public void can_load_a_game_with_a_removed_entity_and_then_save_it() {
        SaveGame game = new SaveGame();
        Zone zone = new Zone(1);
        game.addZone(zone);

        Level level = zone.getLevels().get(0);
        for (int i = 0; i < 5; i++) {
            NPC npc = EntityFactory.golem();
            level.addEntityAtPosition(npc, level.getEmptyTile(npc));
        }
        gameService.saveGame(game);

        Entity testNpc = level.getEntities().get(0);

        assertThat(level.getEntities().size()).isEqualTo(5);
        level.removeEntity(testNpc);
        assertThat(level.getEntities().size()).isEqualTo(4);
        gameService.saveGame(game);

        Level loadedLevel = gameService.loadLevel(level.getId());
        assertThat(loadedLevel.getEntities().size()).isEqualTo(4);

        gameService.saveLevel(loadedLevel);
    }

}