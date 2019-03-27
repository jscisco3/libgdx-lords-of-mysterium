package com.jscisco.lom.dungeon;

import com.artemis.World;
import com.jscisco.lom.util.Size3D;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestDungeon {

    @Test
    @Disabled
    public void creating_a_dungeon_should_be_correct_size() {

        int WIDTH = 10;
        int HEIGHT = 20;
        int DEPTH = 30;

        Dungeon dungeon = new Dungeon(new Size3D(WIDTH, HEIGHT, DEPTH), new World());

        Block[][][] blocks = dungeon.getBlocks();

        Assertions.assertThat(blocks.length).isEqualTo(DEPTH);
        Assertions.assertThat(blocks[0].length).isEqualTo(HEIGHT);
        Assertions.assertThat(blocks[0][0].length).isEqualTo(WIDTH);
    }
}
