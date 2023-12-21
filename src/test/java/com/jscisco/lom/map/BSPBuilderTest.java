package com.jscisco.lom.map;

import com.jscisco.lom.domain.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squidpony.squidmath.RNG;

import java.text.MessageFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BSPBuilderTest {

    private RNG rng;
    private BuildData buildData;
    private BSPBuilder builder;

    @BeforeEach
    public void setUp() {
        rng = new RNG(0xDEADBEEFL);
        builder = new BSPBuilder();
    }

    @Test
    public void addSubRectsAddsTheAppropriateRects() {
        List<Rect> subRects = builder.addSubRects(new Rect(Position.of(0, 0), Position.of(12, 12)));
        assertEquals(4, subRects.size());
    }

    @Test
    public void testSmallRoomIsPossible() {
        Level level = new Level(1, 50, 50);
        Rect room = new Rect(Position.of(4, 4), Position.of(8, 8));
        assertTrue(builder.isPossible(room, level));
    }

    @Test
    public void testLargeRoomIsImpossible() {
        Level level = new Level(1, 50, 50);
        Rect room = new Rect(Position.of(4, 4), Position.of(51, 51));
        assertFalse(builder.isPossible(room, level));
    }

    @Test
    public void testRoomIsImpossibleIfOverlapsWithExistingRoom() {
        Level level = new Level(1, 50, 50);
        Utils.applyRoomToLevel(level, new Rect(Position.of(5, 5), Position.of(10, 10)));
        Rect candidate = new Rect(Position.of(3, 3), Position.of(7, 7));
        assertFalse(builder.isPossible(candidate, level));
    }

    @Test
    public void testWholeBuilder() {
        buildData = new BuildData(1, 50, 50);
        builder.buildMap(rng, buildData);
        assertTrue(buildData.getHistory().size() > 1,
                MessageFormat.format("Expected more than one room, but got {0}", buildData.getHistory().size()));
    }
}
