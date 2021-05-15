package com.jscisco.lom.domain.zone;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.EntityFactory;
import com.jscisco.lom.domain.entity.Hero;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LevelTest {

    private Level level;

    @Test
    public void whenStageIsCreated_itHasWalls() {
        // TODO: Fix
        level = new Level();

        assertThat(level.getTileAt(Position.of(0, 0)).getFeature()).isEqualTo(FeatureFactory.WALL);
        assertThat(level.getTileAt(Position.of(level.getWidth() - 1, level.getHeight() - 1)).getFeature()).isEqualTo(FeatureFactory.WALL);
        assertThat(level.getTileAt(Position.of(0, level.getHeight() - 1)).getFeature()).isEqualTo(FeatureFactory.WALL);
        assertThat(level.getTileAt(Position.of(level.getWidth() - 1, 0)).getFeature()).isEqualTo(FeatureFactory.WALL);

        assertThat(level.getTileAt(Position.of(level.getWidth() / 2, level.getHeight() / 2)).getFeature()).isEqualTo(FeatureFactory.FLOOR);
    }

    @Test
    public void whenActorAddedToStage_positionAndTileUpdatedAppropriately() {
        //TODO: Fix
        level = new Level();
        Hero p = EntityFactory.player();
        Position expectedPosition = Position.of(5, 5);

        level.addEntityAtPosition(p, expectedPosition);

        assertThat(p.getPosition()).isEqualTo(expectedPosition);
        assertThat(level.getEntityAtPosition(expectedPosition).isPresent()).isTrue();
        assertThat(level.getEntityAtPosition(expectedPosition).get()).isEqualTo(p);
        assertThat(p.getLevel()).isEqualTo(level);
    }

}
