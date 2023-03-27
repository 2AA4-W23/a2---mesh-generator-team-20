package ca.mcmaster.cas.se2aa4.a3.island.lake;

import ca.mcmaster.cas.se2aa4.a3.island.shape.AppleShape;
import ca.mcmaster.cas.se2aa4.a3.island.shape.ShapeProvider;
import ca.mcmaster.cas.se2aa4.a3.island.utils.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EllipseLakeProviderTest {

    @Test
    void isLake_returnsTrueForCoordinatesInsideLakes() {
        ShapeProvider shapeProvider = new AppleShape(100, 100, 0.5);
        EllipseLakeProvider lakeProvider = new EllipseLakeProvider(100, 100, shapeProvider, 2);
        Coordinate lakeCenter1 = new Coordinate(30, 50);
        Coordinate lakeCenter2 = new Coordinate(80, 70);

        assertTrue(lakeProvider.isLake(lakeCenter1));
        assertTrue(lakeProvider.isLake(lakeCenter2));
    }

    @Test
    void isLake_returnsFalseForCoordinatesOutsideLakes() {
        ShapeProvider shapeProvider = new AppleShape(100, 100, 0.5);
        EllipseLakeProvider lakeProvider = new EllipseLakeProvider(100, 100, shapeProvider, 2);
        Coordinate outsideLake1 = new Coordinate(20, 20);
        Coordinate outsideLake2 = new Coordinate(90, 10);

        assertFalse(lakeProvider.isLake(outsideLake1));
        assertFalse(lakeProvider.isLake(outsideLake2));
    }
}


