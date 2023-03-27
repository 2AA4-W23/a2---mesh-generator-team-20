package ca.mcmaster.cas.se2aa4.a3.island.shape;

import ca.mcmaster.cas.se2aa4.a3.island.utils.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerlinShapeTest {

    @Test
    void testIsLand() {
        PerlinShape perlinShape = new PerlinShape(1234L);

        // Test some coordinates that are expected to be land and water
        assertEquals(perlinShape.isLand(new Coordinate(0, 0)), false);
        assertEquals(perlinShape.isLand(new Coordinate(0, 100)), true);
        assertEquals(perlinShape.isLand(new Coordinate(100, 0)), true);
        assertEquals(perlinShape.isLand(new Coordinate(100, 100)), true);
    }
}
