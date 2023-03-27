package ca.mcmaster.cas.se2aa4.a3.island.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {
    @Test
    public void testConstructorAndGetters() {
        Coordinate c = new Coordinate(1.0, 2.0);
        assertEquals(1.0, c.x, 0.001);
        assertEquals(2.0, c.y, 0.001);
    }

    @Test
    public void testEqualsAndHashCode() {
        Coordinate c1 = new Coordinate(1.0, 2.0);
        Coordinate c2 = new Coordinate(1.0, 2.0);
        Coordinate c3 = new Coordinate(2.0, 1.0);

        assertTrue(c1.equals(c2));
        assertTrue(c2.equals(c1));
        assertFalse(c1.equals(c3));
        assertFalse(c2.equals(c3));

        assertEquals(c1.hashCode(), c2.hashCode());
        assertNotEquals(c1.hashCode(), c3.hashCode());
        assertNotEquals(c2.hashCode(), c3.hashCode());
    }

    @Test
    public void testDistance() {
        Coordinate c1 = new Coordinate(1.0, 2.0);
        Coordinate c2 = new Coordinate(4.0, 6.0);

        assertEquals(5.0, c1.distance(c2), 0.001);
        assertEquals(5.0, c2.distance(c1), 0.001);
    }
}
