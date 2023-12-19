package com.jscisco.lom.map;

import com.jscisco.lom.domain.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectTest {

    @Test
    public void testRoomIntersectsWhenTheyDoNot() {
        Rect one = new Rect(Position.of(0, 0), Position.of(1, 1));
        Rect two = new Rect(Position.of(2, 2), Position.of(3, 3));

        assertFalse(one.intersects(two));
        assertFalse(two.intersects(one));
    }

    @Test
    public void testRoomIntersectsWhenTheyDo() {
        Rect one = new Rect(Position.of(0, 0), Position.of(2, 2));
        Rect two = new Rect(Position.of(1, 1), Position.of(3, 3));

        assertTrue(one.intersects(two));
        assertTrue(two.intersects(one));
    }

    @Test
    public void centerIsCalculatedAppropriately() {
        Rect room = new Rect(Position.of(0, 0), Position.of(2, 2));
        Position center = room.center();

        assertEquals(Position.of(1, 1), center);
    }
}
