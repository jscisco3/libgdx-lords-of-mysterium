package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.EntityFactory;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.zone.Door;
import com.jscisco.lom.shelf.Level;
import com.jscisco.lom.domain.zone.LevelGeneratorStrategy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OpenDoorActionTest {

    Level level = new Level(25, 25, new LevelGeneratorStrategy.EmptyLevelStrategy());
    Hero hero = EntityFactory.player();

    @Test
    public void openDoorAction_successfulIfTargetHasAClosedDoor() {
        Door door = new Door();
        door.close();
        Position p = Position.of(10, 10);
        level.getTile(p).setFeature(door);
        OpenDoorAction action = new OpenDoorAction(hero, level.getTile(p));

        action.execute();
        assertThat(door.isOpen()).isTrue();
    }

}
