package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.LevelGeneratorStrategy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FieldOfViewTest {

    @Test
    public void whenFieldOfViewIsCalculated_tilesAreExplored() {
        Level level = new Level(10, 10, new LevelGeneratorStrategy.EmptyLevelStrategy());

        Hero hero = new Hero.Builder()
                .withPosition(Position.of(1, 1))
                .build();

        level.addHero(hero);

        assertThat(level.getTileAt(Position.of(1, 1)).isExplored()).isTrue();
        assertThat(level.getTileAt(Position.of(2, 2)).isExplored()).isTrue();
        assertThat(level.getTileAt(Position.of(9, 9)).isExplored()).isFalse();
    }

    @Test
    public void afterMovingHero_newTilesAreExplored() {

        Level level = new Level(10, 10, new LevelGeneratorStrategy.EmptyLevelStrategy());

        Hero hero = new Hero.Builder()
                .withPosition(Position.of(1, 1))
                .build();

        level.addHero(hero);

        assertThat(level.getTileAt(Position.of(1, 1)).isExplored()).isTrue();
        assertThat(level.getTileAt(Position.of(2, 2)).isExplored()).isTrue();
        assertThat(level.getTileAt(Position.of(9, 9)).isExplored()).isFalse();

        // Move the hero and recalculate
        hero.move(Position.of(8, 8));
        hero.calculateFieldOfView();
        assertThat(level.getTileAt(Position.of(9, 9)).isExplored()).isTrue();
    }

}
