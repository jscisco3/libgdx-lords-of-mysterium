package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.Direction;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.actor.ActorFactory;
import com.jscisco.lom.domain.actor.Player;
import com.jscisco.lom.domain.zone.Door;
import com.jscisco.lom.domain.zone.Stage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WalkActionTest {

    Stage stage = new Stage();
    Player player = ActorFactory.player();

    @Test
    public void walkActionSuccessfullyMovesActor_ifTargetSpaceIsWalkable() {
        WalkAction action = new WalkAction(player, stage, Direction.N);
        Position startPosition = Position.of(10, 10);
        stage.addActorAtPosition(player, startPosition);

        ActionResult result = action.execute();

        assertThat(result.success()).isTrue();
        assertThat(player.getPosition()).isEqualTo(startPosition.add(Direction.N.relativePosition));
        assertThat(stage.getTileAt(startPosition).isOccupied()).isFalse();
        assertThat(stage.getTileAt(startPosition.add(Direction.N.relativePosition)).isOccupied()).isTrue();
    }

    @Test
    public void walkActionFails_ifTargetSpaceIsNotWalkable() {
        WalkAction action = new WalkAction(player, stage, Direction.W);
        Position startPosition = Position.of(1, 1);
        stage.addActorAtPosition(player, startPosition);

        ActionResult result = action.execute();

        assertThat(result.success()).isFalse();
    }

    @Test
    public void walkActionAlternatesToOpenDoorAction_ifTargetSpaceIsClosedDoor() {
        WalkAction action = new WalkAction(player, stage, Direction.E);
        Position startPosition = Position.of(1, 1);
        stage.addActorAtPosition(player, startPosition);
        Position endPosition = startPosition.add(Direction.E.relativePosition);

        Door door = new Door();
        door.close();
        stage.getTileAt(endPosition).setFeature(door);

        ActionResult result = action.execute();

        assertThat(result.getAlternative()).isInstanceOf(OpenDoorAction.class);
    }
}
