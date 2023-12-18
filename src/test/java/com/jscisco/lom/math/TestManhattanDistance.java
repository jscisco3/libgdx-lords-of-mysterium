package com.jscisco.lom.math;

import com.jscisco.lom.domain.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestManhattanDistance {

    @Test
    public void testManhattanDistance() {
        Position start = Position.of(0, 0);
        Position end = Position.of(10, 10);

        float distance = DistanceAlgorithm.manhattan(start, end);
        assertEquals(distance, 10.0, 0.0001);
    }
}
