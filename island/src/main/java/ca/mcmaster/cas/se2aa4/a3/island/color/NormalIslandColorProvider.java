package ca.mcmaster.cas.se2aa4.a3.island.color;

import ca.mcmaster.cas.se2aa4.a3.island.elevation.ElevationProvider;
import ca.mcmaster.cas.se2aa4.a3.island.lake.LakeProvider;
import ca.mcmaster.cas.se2aa4.a3.island.shape.ShapeProvider;
import ca.mcmaster.cas.se2aa4.a3.island.utils.Coordinate;

public class NormalIslandColorProvider implements ColorProvider {
    private final ElevationProvider elevationProvider;
    private final ShapeProvider shapeProvider;
    private final LakeProvider lakeProvider;

    public NormalIslandColorProvider(ElevationProvider elevationProvider, ShapeProvider shapeProvider, LakeProvider lakeProvider) {
        this.elevationProvider = elevationProvider;
        this.shapeProvider = shapeProvider;
        this.lakeProvider = lakeProvider;
    }

    public Color getColor(Coordinate coordinate) {
        boolean isLand = shapeProvider.contains(coordinate);
        if (isLand) {
            boolean isLake = lakeProvider.isLake(coordinate);
            if (isLake) {
                return new Color(0, 0, 200);
            } else {
                double elevation = elevationProvider.getElevation(coordinate);
                return new Color((int) (elevation * 255));
            }
        } else {
            return new Color(150, 210, 255);
        }
    }
}
