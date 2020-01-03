package com.jscisco.lom.zone;

import com.jscisco.lom.entity.EntityName;
import com.jscisco.lom.entity.FieldOfView;
import com.jscisco.lom.entity.Player;
import com.jscisco.lom.util.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class TestStage {

    private int WIDTH = 100;
    private int HEIGHT = 50;
    private Stage stage;

    @BeforeEach
    public void setUp() {
        this.stage = new Stage(WIDTH, HEIGHT);
    }

    @Test
    public void findingAnEmptyPositionShouldFindAPosition() {
        Optional<Position> p = this.stage.findEmptyPosition();
        Assertions.assertThat(p).isNotNull();
    }

    @Disabled
    @Test
    public void findingAnEmptyPositionThatFailsShouldReturnNull() {

    }

    @Test
    public void updatingTilesBasedOnFOVShouldFailIfThereIsNoPlayer() {
        Assertions.assertThatThrownBy(() -> {
            stage.updateTilesBasedOnFOV();
        }).isInstanceOf(AssertionError.class);
    }

    @Test
    public void updatingTilesBasedOnFOVShouldSucceedIfThereIsAPlayer() {
        stage.findEmptyPosition().ifPresent(pos -> {
            Player p = new Player.Builder(new EntityName("HERO"))
                    .withStage(stage)
                    .withPosition(pos)
                    .withFieldOfView(new FieldOfView(10f))
                    .build();
            stage.addEntity(p);
            stage.updateTilesBasedOnFOV();
        });
        Player p = stage.getPlayer();
        Position position = p.getPosition();
        stage.addEntity(p);
        stage.updateTilesBasedOnFOV();
        Assertions.assertThat(stage.getTileAt(position).isInFov()).isTrue();
        Assertions.assertThat(stage.getTileAt(position).isSeen()).isTrue();
    }

    // Test processing here

    @Test
    void convertingStageToSquidlibMapPutsDotInWalkableTiles() {
        char[][] map = stage.toSquidlibMap();
        for (int x = 0; x < stage.getWidth(); x++) {
            for (int y = 0; y < stage.getHeight(); y++) {
                if (stage.getTileAt(Position.get(x, y)).getTerrain().isWalkable()) {
                    Assertions.assertThat(map[x][y]).isEqualTo('.');
                }
            }
        }
    }


    @Test
    void convertingStageToSquidlibMapPutsHashInNonWalkableTiles() {
        char[][] map = stage.toSquidlibMap();
        for (int x = 0; x < stage.getWidth(); x++) {
            for (int y = 0; y < stage.getHeight(); y++) {
                if (!stage.getTileAt(Position.get(x, y)).getTerrain().isWalkable()) {
                    Assertions.assertThat(map[x][y]).isEqualTo('#');
                }
            }
        }
    }

}
