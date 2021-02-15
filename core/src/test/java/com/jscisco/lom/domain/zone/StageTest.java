package com.jscisco.lom.domain.zone;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.ActorFactory;
import com.jscisco.lom.domain.entity.Player;
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

    @Test
    public void whenActorAddedToStage_positionAndTileUpdatedAppropriately() {
        stage = new Stage();
        Player p = ActorFactory.player();
        Position expectedPosition = Position.of(5, 5);

        stage.addActorAtPosition(p, expectedPosition);

        assertThat(p.getPosition()).isEqualTo(expectedPosition);
        assertThat(stage.getTileAt(expectedPosition).isOccupied()).isTrue();
        assertThat(stage.getTileAt(expectedPosition).getOccupant()).isEqualTo(p);
    }

}
