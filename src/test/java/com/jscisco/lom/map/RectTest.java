package com.jscisco.lom.map;

import com.jscisco.lom.domain.Position;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.List;

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

    @Test
    public void divideVertically() {
        Rect room = new Rect(Position.of(0, 0), 100, 100);
        List<Rect> rects = room.divideVertically(50);
        Rect left = rects.getFirst();
        Rect right = rects.getLast();
        assertEquals(left.getBottomLeft(), room.getBottomLeft());
        assertEquals(Position.of(50, 0), right.getBottomLeft());
        assertEquals(left.width, 50, MessageFormat.format("Left width is expected to be 50, but was {0}", left.width));
        assertEquals(right.width, 50,
                MessageFormat.format("Right width is expected to be 50, but was {0}", right.width));
    }

    @Test
    public void divideVerticallyInterestingly() {
        Rect room = new Rect(Position.of(0, 0), 100, 100);
        List<Rect> rects = room.divideVertically(25);
        Rect left = rects.getFirst();
        Rect right = rects.getLast();
        assertEquals(left.getBottomLeft(), room.getBottomLeft());
        assertEquals(Position.of(25, 0), right.getBottomLeft());
        assertEquals(25, left.width, MessageFormat.format("Left width is expected to be 50, but was {0}", left.width));
        assertEquals(75, right.width,
                MessageFormat.format("Right width is expected to be 50, but was {0}", right.width));
    }

    @Test
    public void divideHorizontally() {
        Rect room = new Rect(Position.of(0, 0), 100, 100);
        List<Rect> rects = room.divideHorizontally(50);
        Rect top = rects.getFirst();
        Rect bottom = rects.getLast();
        assertEquals(Position.of(0, 50), top.getBottomLeft(),
                MessageFormat.format("Top Bottom left is {0}", top.getBottomLeft()));
        assertEquals(Position.of(99, 99), top.getTopRight());
        assertEquals(Position.of(0, 0), bottom.getBottomLeft());
        assertEquals(Position.of(99, 49), bottom.getTopRight());
        assertEquals(top.height, 50, MessageFormat.format("Top height is expected to be 50, but was {0}", top.width));
        assertEquals(bottom.height, 50,
                MessageFormat.format("Bottom height is expected to be 50, but was {0}", bottom.width));
    }

    @Test
    public void divideHorizontallyInterestingly() {
        Rect room = new Rect(Position.of(0, 0), 100, 100);
        List<Rect> rects = room.divideHorizontally(25);
        Rect top = rects.getFirst();
        Rect bottom = rects.getLast();
        assertEquals(top.getBottomLeft(), Position.of(0, 25));
        assertEquals(top.getTopRight(), Position.of(99, 99));
        assertEquals(bottom.getBottomLeft(), Position.of(0, 0));
        assertEquals(bottom.getTopRight(), Position.of(99, 24));
        assertEquals(top.height, 75, MessageFormat.format("Top height is expected to be 75, but was {0}", top.width));
        assertEquals(bottom.height, 25,
                MessageFormat.format("Bottom height is expected to be 25, but was {0}", bottom.width));
    }
}
