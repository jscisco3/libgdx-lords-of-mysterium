package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.EntityFactory;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.zone.Door;
import com.jscisco.lom.domain.zone.Level;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OpenDoorActionTest {

    // TODO: Fix
    Level level = new Level();
    Hero hero = EntityFactory.player();

    @Test
    public void openDoorAction_successfulIfTargetHasAClosedDoor() {
        Door door = new Door();
        door.close();
        Position p = Position.of(10, 10);
        level.getTileAt(p).setFeature(door);
        OpenDoorAction action = new OpenDoorAction(hero, level.getTileAt(p));

        action.execute();
        assertThat(door.isOpen()).isTrue();
    }

}
