package ca.mcmaster.cas.se2aa4.a3.island.lake;

import ca.mcmaster.cas.se2aa4.a3.island.shape.ShapeProvider;
import ca.mcmaster.cas.se2aa4.a3.island.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class CircleLakeProvider extends LakeProvider {
    private record Lake(Coordinate center, double radius) {
    }

    private final List<Lake> lakes = new ArrayList<>();

    public CircleLakeProvider(double width, double height, ShapeProvider shapeProvider, int lakeCount) {
        while (lakes.size() < lakeCount) {
            Coordinate center = new Coordinate(Math.random() * width, Math.random() * height);
            double radius = Math.random() * (width / 8);
            if (shapeProvider.isLand(center) && radius > 50 && shapeProvider.nearestBorder(center).distance(center) > (radius + 50)) {
                lakes.add(new Lake(center, radius));
            }
        }
    }

    public boolean isLake(Coordinate coordinate) {
        for (Lake lake : lakes) {
            if (coordinate.distance(lake.center) < lake.radius) {
                return true;
            }
        }
        return false;
    }
}
