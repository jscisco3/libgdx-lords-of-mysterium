package com.jscisco.lom.zone;

import com.jscisco.lom.entity.Player;
import com.jscisco.lom.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestStageImpl {

    private int WIDTH = 100;
    private int HEIGHT = 50;
    private StageImpl stage;

    @BeforeEach
    public void setUp() {
        this.stage = new StageImpl(WIDTH, HEIGHT);
    }

    @Test
    public void findingAnEmptyPositionShouldFindAPosition() {
        Position p = this.stage.findEmptyPosition();
        Assertions.assertNotNull(p);
    }

    @Disabled
    @Test
    public void findingAnEmptyPositionThatFailsShouldReturnNull() {

    }

    @Test
    public void updatingTilesBasedOnFOVShouldFailIfThereIsNoPlayer() {
        Assertions.assertThrows(AssertionError.class, () -> stage.updateTilesBasedOnFOV());
    }

    @Test
    public void updatingTilesBasedOnFOVShouldSucceedIfThereIsAPlayer() {
        Player p = new Player(stage, stage.findEmptyPosition());
        Position position = p.getPosition();
        stage.addEntity(p);
        stage.updateTilesBasedOnFOV();
        Assertions.assertTrue(stage.getTileAt(position).isInFov());
        Assertions.assertTrue(stage.getTileAt(position).isSeen());
    }

    // Test processing here

}
