package ca.mcmaster.cas.se2aa4.a3.island.shape;

import org.junit.Test;
import static org.junit.Assert.*;

import ca.mcmaster.cas.se2aa4.a3.island.utils.Coordinate;

public class AppleShapeTest {

    @Test
    public void testIsLand() {
        AppleShape shape = new AppleShape(10.0, 10.0, 2.0);

        // Test points inside the shape
        assertTrue(shape.isLand(new Coordinate(5.0, 5.0))); // Center of shape
        assertTrue(shape.isLand(new Coordinate(6.0, 5.0))); // Left of center
        assertTrue(shape.isLand(new Coordinate(4.0, 5.0))); // Right of center
        assertTrue(shape.isLand(new Coordinate(5.0, 6.0))); // Below center
    }
}

