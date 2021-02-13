package com.jscisco.lom.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StageTest {

    private Stage stage;

    @Test
    public void whenStageIsCreated_itHasWalls() {
        stage = new Stage();

        assertThat(stage.getTileAt(Position.of(0, 0)).feature).isEqualTo(FeatureFactory.WALL);
        assertThat(stage.getTileAt(Position.of(stage.getWidth() - 1, stage.getHeight() - 1)).feature).isEqualTo(FeatureFactory.WALL);
        assertThat(stage.getTileAt(Position.of(0, stage.getHeight() - 1)).feature).isEqualTo(FeatureFactory.WALL);
        assertThat(stage.getTileAt(Position.of(stage.getWidth() - 1, 0)).feature).isEqualTo(FeatureFactory.WALL);

        assertThat(stage.getTileAt(Position.of(stage.getWidth() / 2, stage.getHeight() / 2)).feature).isEqualTo(FeatureFactory.FLOOR);
    }

}
