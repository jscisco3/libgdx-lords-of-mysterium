package com.jscisco.lom.domain;

import com.jscisco.lom.domain.zone.FeatureFactory;
import com.jscisco.lom.domain.zone.Stage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StageTest {

    private Stage stage;

    @Test
    public void whenStageIsCreated_itHasWalls() {
        stage = new Stage();

        assertThat(stage.getTileAt(Position.of(0, 0)).getFeature()).isEqualTo(FeatureFactory.WALL);
        assertThat(stage.getTileAt(Position.of(stage.getWidth() - 1, stage.getHeight() - 1)).getFeature()).isEqualTo(FeatureFactory.WALL);
        assertThat(stage.getTileAt(Position.of(0, stage.getHeight() - 1)).getFeature()).isEqualTo(FeatureFactory.WALL);
        assertThat(stage.getTileAt(Position.of(stage.getWidth() - 1, 0)).getFeature()).isEqualTo(FeatureFactory.WALL);

        assertThat(stage.getTileAt(Position.of(stage.getWidth() / 2, stage.getHeight() / 2)).getFeature()).isEqualTo(FeatureFactory.FLOOR);
    }

}
