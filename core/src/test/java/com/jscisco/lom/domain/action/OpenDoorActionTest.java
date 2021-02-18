package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.ActorFactory;
import com.jscisco.lom.domain.entity.Player;
import com.jscisco.lom.domain.zone.Door;
import com.jscisco.lom.domain.zone.Stage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OpenDoorActionTest {

    Stage stage = new Stage();
    Player player = ActorFactory.player();

    @Test
    @Disabled
    public void openDoorAction_successfulIfTargetHasAClosedDoor() {
        Door door = new Door();
        door.close();
        Position p = Position.of(10, 10);
        stage.getTileAt(p).setFeature(door);
        OpenDoorAction action = new OpenDoorAction(player, stage.getTileAt(p));

        action.execute();
        assertThat(door.isOpen()).isTrue();
    }

}
