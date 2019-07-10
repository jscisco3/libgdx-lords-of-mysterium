package com.jscisco.lom.dungeon;

import com.jscisco.lom.util.Size3D;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestZone {

    @Disabled
    @Test
    public void creating_a_dungeon_should_be_correct_size() {

        int WIDTH = 20;
        int HEIGHT = 20;
        int DEPTH = 1;

        Zone zone = new Zone(new Size3D(WIDTH, HEIGHT, DEPTH));

        Block[][][] blocks = zone.getBlocks();

        Assertions.assertThat(blocks.length).isEqualTo(DEPTH);
        Assertions.assertThat(blocks[0].length).isEqualTo(HEIGHT);
        Assertions.assertThat(blocks[0][0].length).isEqualTo(WIDTH);
    }
}
