package com.jscisco.lom.map;

import com.jscisco.lom.domain.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    @Test
    public void testRoomIntersectsWhenTheyDoNot() {
        Room one = new Room(Position.of(0, 0), Position.of(1, 1));
        Room two = new Room(Position.of(2, 2), Position.of(3, 3));

        assertFalse(one.intersects(two));
        assertFalse(two.intersects(one));
    }

    @Test
    public void testRoomIntersectsWhenTheyDo() {
        Room one = new Room(Position.of(0, 0), Position.of(2, 2));
        Room two = new Room(Position.of(1, 1), Position.of(3, 3));

        assertTrue(one.intersects(two));
        assertTrue(two.intersects(one));
    }

    @Test
    public void centerIsCalculatedAppropriately() {
        Room room = new Room(Position.of(0, 0), Position.of(2, 2));
        Position center = room.center();

        assertEquals(Position.of(1, 1), center);
    }
}
