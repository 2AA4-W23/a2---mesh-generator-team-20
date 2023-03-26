package ca.mcmaster.cas.se2aa4.a3.island.soil;

import ca.mcmaster.cas.se2aa4.a3.island.shape.ShapeProvider;
import ca.mcmaster.cas.se2aa4.a3.island.utils.Coordinate;

public class BasicSoilAbsorptionProvider implements SoilAbsorptionProvider {
    private final ShapeProvider shapeProvider;

    public BasicSoilAbsorptionProvider(ShapeProvider shapeProvider) {
        this.shapeProvider = shapeProvider;
    }

    public double getAbsorptionLevel(Coordinate coordinate) {
        if (shapeProvider.contains(coordinate)) {
            Coordinate nearestSea = shapeProvider.nearestBorder(coordinate);
            double distance = coordinate.distance(nearestSea);
            return Math.min(1, 1 / (distance + 5));
        } else {
            return 1;
        }
    }
}
