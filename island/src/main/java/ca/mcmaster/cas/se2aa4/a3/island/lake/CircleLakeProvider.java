package ca.mcmaster.cas.se2aa4.a3.island.lake;

import ca.mcmaster.cas.se2aa4.a3.island.elevation.ElevationProvider;
import ca.mcmaster.cas.se2aa4.a3.island.shape.ShapeProvider;
import ca.mcmaster.cas.se2aa4.a3.island.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class CircleLakeProvider implements LakeProvider {
    private record Lake(Coordinate center, double radius) {
    }

    private final List<Lake> lakes = new ArrayList<>();

    public CircleLakeProvider(double width, double height, ShapeProvider shapeProvider, int lakeCount) {
        while (lakes.size() < lakeCount) {
            double x = Math.random() * width;
            double y = Math.random() * height;
            if (shapeProvider.contains(new Coordinate(x, y))) {
                lakes.add(new Lake(new Coordinate(x, y), Math.random() * (width / 16)));
            }
        }
    }

    public boolean isLake(Coordinate coordinate) {
        for (Lake lake : lakes) {
            double dx = coordinate.x - lake.center.x;
            double dy = coordinate.y - lake.center.y;
            double distance = Math.sqrt(dx * dx + dy * dy);
            if (distance < lake.radius) {
                return true;
            }
        }
        return false;
    }
}
