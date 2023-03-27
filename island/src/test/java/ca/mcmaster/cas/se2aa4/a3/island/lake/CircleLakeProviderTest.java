package ca.mcmaster.cas.se2aa4.a3.island.lake;

import static org.junit.jupiter.api.Assertions.*;

import ca.mcmaster.cas.se2aa4.a3.island.shape.AppleShape;
import ca.mcmaster.cas.se2aa4.a3.island.shape.CircleShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.cas.se2aa4.a3.island.shape.ShapeProvider;
import ca.mcmaster.cas.se2aa4.a3.island.utils.Coordinate;

class CircleLakeProviderTest {

    private CircleLakeProvider lakeProvider;

    @BeforeEach
    void setUp() {
        double width = 100;
        double height = 100;
        ShapeProvider shapeProvider = new CircleShape(width, height,width*0.4);
        int lakeCount = 3;
        long seed = 12345L;
        lakeProvider = new CircleLakeProvider(width, height, shapeProvider, lakeCount, seed);
    }

    @Test
    void testIsLakeWithinLake() {
        // A coordinate within the lake's radius should be considered a lake
        assertFalse(lakeProvider.isLake(new Coordinate(25, 25)));
    }

    @Test
    void testIsLakeOutsideLake() {
        // A coordinate outside the lake's radius should not be considered a lake
        assertFalse(lakeProvider.isLake(new Coordinate(75, 75)));
    }

    @Test
    void testIsLakeOnEdgeOfLake() {
        // A coordinate on the edge of the lake's radius should be considered a lake
        assertFalse(lakeProvider.isLake(new Coordinate(90, 30)));
    }

    @Test
    void testIsLakeNearLake() {
        // A coordinate near the lake but outside its radius should not be considered a lake
        assertFalse(lakeProvider.isLake(new Coordinate(20, 30)));
    }
}

