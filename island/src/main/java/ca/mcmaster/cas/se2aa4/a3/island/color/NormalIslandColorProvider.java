package ca.mcmaster.cas.se2aa4.a3.island.color;

import ca.mcmaster.cas.se2aa4.a3.island.elevation.ElevationProvider;
import ca.mcmaster.cas.se2aa4.a3.island.shape.ShapeProvider;

public class NormalIslandColorProvider implements ColorProvider {
    private final ElevationProvider elevationProvider;
    private final ShapeProvider shapeProvider;

    public NormalIslandColorProvider(ElevationProvider elevationProvider, ShapeProvider shapeProvider) {
        this.elevationProvider = elevationProvider;
        this.shapeProvider = shapeProvider;
    }

    public Color getColor(double x, double y) {
        boolean isLand = shapeProvider.contains(x, y);
        if (isLand) {
            double elevation = elevationProvider.getElevation(x, y);
            return new Color(0, (int) (elevation * 255), 0);
        } else {
            return new Color(0, 0, 255);
        }
    }
}
