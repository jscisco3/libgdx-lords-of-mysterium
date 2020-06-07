package com.jscisco.lom.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class LevelTest {

    @Test
    public void canCreateALevelWithoutAPlayer() {
        Level level = new Level.Builder()
                .withSize(10, 10)
                .build();
        assertThat(level).isNotNull();
    }

    @Test
    public void cannotCreateALevelWithoutASize() {
        Throwable throwable = catchThrowable(() -> new Level.Builder().build());
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void canAddAnEntityToAnEmptyPosition() {
        Level level = new Level.Builder()
                .withSize(10, 15)
                .build();
        boolean added = level.addEntityAtPosition(GameObject.npc(new EntityName("Test Entity"), new Health(100)), Position.of(1, 1));
        assertThat(added).isTrue();
    }

}
