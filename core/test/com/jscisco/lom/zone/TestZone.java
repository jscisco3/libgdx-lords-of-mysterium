package com.jscisco.lom.zone;

import com.jscisco.lom.util.Size3D;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestZone {

    @Test
    public void creating_a_dungeon_should_be_correct_size() {

        int WIDTH = 20;
        int HEIGHT = 20;
        int DEPTH = 1;

        Zone zone = new Zone(new Size3D(WIDTH, HEIGHT, DEPTH), null);

        Stage stage = zone.getCurrentStage();

        Assertions.assertThat(zone.getStages().size()).isEqualTo(DEPTH);
        Assertions.assertThat(stage.getTiles().length).isEqualTo(WIDTH);
        Assertions.assertThat(stage.getTiles()[0].length).isEqualTo(WIDTH);
    }
}
