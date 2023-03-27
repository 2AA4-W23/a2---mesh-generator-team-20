package ca.mcmaster.cas.se2aa4.a3.island.shape;

import ca.mcmaster.cas.se2aa4.a3.island.shape.CircleShape;
import ca.mcmaster.cas.se2aa4.a3.island.utils.Coordinate;
import org.junit.Test;
import static org.junit.Assert.*;

public class CircleShapeTest {

    @Test
    public void testIsLand() {
        double width = 10.0;
        double height = 10.0;
        double radius = 5.0;
        CircleShape circle = new CircleShape(width, height, radius);

        // Test points within and outside of the circle
        Coordinate coord1 = new Coordinate(5.0, 5.0);
        Coordinate coord2 = new Coordinate(7.0, 7.0);
        Coordinate coord3 = new Coordinate(8.0, 1.0);
        assertTrue(circle.isLand(coord1));
        assertTrue(circle.isLand(coord2));

        // Test points on the boundary of the circle
        Coordinate coord4 = new Coordinate(5.0, 0.0);
        Coordinate coord5 = new Coordinate(0.0, 5.0);
        Coordinate coord6 = new Coordinate(5.0, 10.0);
        Coordinate coord7 = new Coordinate(10.0, 5.0);
        assertTrue(circle.isLand(coord4));
        assertTrue(circle.isLand(coord5));
        assertTrue(circle.isLand(coord6));
        assertTrue(circle.isLand(coord7));
    }
}
