package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.Direction;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.ActorFactory;
import com.jscisco.lom.domain.entity.Player;
import com.jscisco.lom.domain.zone.Door;
import com.jscisco.lom.domain.zone.Level;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WalkActionTest {

    Level level = new Level();
    Player player = ActorFactory.player();

    @Test
    public void walkActionSuccessfullyMovesActor_ifTargetSpaceIsWalkable() {
        Position startPosition = Position.of(10, 10);
        level.addEntityAtPosition(player, startPosition);
        WalkAction action = new WalkAction(player, Direction.N);
        assertThat(player.getStage()).isNotNull();

        ActionResult result = action.execute();

        assertThat(result.success()).isTrue();
        assertThat(player.getPosition()).isEqualTo(startPosition.add(Direction.N.relativePosition));
        assertThat(level.getTileAt(startPosition).isOccupied()).isFalse();
        assertThat(level.getTileAt(startPosition.add(Direction.N.relativePosition)).isOccupied()).isTrue();
    }

    @Test
    public void walkActionFails_ifTargetSpaceIsNotWalkable() {
        Position startPosition = Position.of(1, 1);
        level.addEntityAtPosition(player, startPosition);
        WalkAction action = new WalkAction(player, Direction.W);

        ActionResult result = action.execute();

        assertThat(result.success()).isFalse();
    }

    @Test
    public void walkActionAlternatesToOpenDoorAction_ifTargetSpaceIsClosedDoor() {
        Position startPosition = Position.of(1, 1);
        level.addEntityAtPosition(player, startPosition);
        WalkAction action = new WalkAction(player, Direction.E);
        Position endPosition = startPosition.add(Direction.E.relativePosition);

        Door door = new Door();
        door.close();
        level.getTileAt(endPosition).setFeature(door);

        ActionResult result = action.execute();

        assertThat(result.getAlternative()).isInstanceOf(OpenDoorAction.class);
    }
}
