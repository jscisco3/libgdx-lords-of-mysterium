package com.jscisco.lom.application;

import com.jscisco.lom.configuration.ApplicationConfiguration;
import com.jscisco.lom.configuration.PersistenceConfiguration;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.SaveGame;
import com.jscisco.lom.domain.entity.EntityFactory;
import com.jscisco.lom.domain.entity.Hero;
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
    public void can_save_entity_with_ai() {

    }

}
