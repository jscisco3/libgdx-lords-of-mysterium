package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.LevelGeneratorStrategy;
import fixtures.EntityFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FieldOfViewTest {

    @Test
    public void whenFieldOfViewIsCalculated_tilesAreExplored() {
        Level level = new Level(10, 10, new LevelGeneratorStrategy.EmptyLevelStrategy());

        Hero hero = EntityFactory.testHero();

        level.addEntityAtPosition(hero, Position.of(1, 1));

        assertThat(level.getTileAt(Position.of(1, 1)).isExplored()).isTrue();
        assertThat(level.getTileAt(Position.of(2, 2)).isExplored()).isTrue();
    }

    @Test
    public void afterMovingHero_newTilesAreExplored() {

        Level level = new Level(10, 10, new LevelGeneratorStrategy.EmptyLevelStrategy());

        Hero hero = EntityFactory.testHero();

        level.addEntityAtPosition(hero, Position.of(1, 1));

        assertThat(level.getTileAt(Position.of(1, 1)).isExplored()).isTrue();
        assertThat(level.getTileAt(Position.of(2, 2)).isExplored()).isTrue();

        // Move the hero and recalculate
        hero.move(Position.of(8, 8));
        hero.calculateFieldOfView();
        assertThat(level.getTileAt(Position.of(9, 9)).isExplored()).isTrue();
    }

}
