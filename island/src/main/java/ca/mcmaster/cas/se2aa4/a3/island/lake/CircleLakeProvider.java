package ca.mcmaster.cas.se2aa4.a3.island.lake;

import ca.mcmaster.cas.se2aa4.a3.island.elevation.ElevationProvider;
import ca.mcmaster.cas.se2aa4.a3.island.shape.ShapeProvider;
import ca.mcmaster.cas.se2aa4.a3.island.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class CircleLakeProvider implements LakeProvider, ElevationProvider {
    private final ElevationProvider elevationProvider;
    private final List<Coordinate> lakes = new ArrayList<>();

    public CircleLakeProvider(double width, double height, ElevationProvider elevationProvider, ShapeProvider shapeProvider, int lakeCount) {
        this.elevationProvider = elevationProvider;

        while (lakes.size() < lakeCount) {
            double x = Math.random() * width;
            double y = Math.random() * height;
            if (shapeProvider.contains(x, y)) {
                lakes.add(new Coordinate(x, y));
            }
        }
    }

    public boolean isLake(double x, double y) {
        double elevation = elevationProvider.getElevation(x, y);
        return true;
//        return elevation < lakeThreshold;
    }

    public double getElevation(double x, double y) {
        return elevationProvider.getElevation(x, y);
    }
}
