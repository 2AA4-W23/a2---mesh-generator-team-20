package ca.mcmaster.cas.se2aa4.a3.island.elevation;

import ca.mcmaster.cas.se2aa4.a3.island.utils.Coordinate;

public class MountainElevation implements ElevationProvider {
    private final double centerX;
    private final double centerY;
    private final double radius;
    private final double mountainHeight;

    public MountainElevation(double width, double height, double mountainHeight) {
        this.centerX = width / 2;
        this.centerY = height / 2;
        this.radius = width / 3 * 2;
        this.mountainHeight = mountainHeight;
    }

    public double getElevation(Coordinate coordinate) {
        double distance = Math.sqrt(Math.pow(coordinate.x - centerX, 2) + Math.pow(coordinate.y - centerY, 2));
        if (distance > radius) {
            return 0;
        }
        return mountainHeight * (1 - distance / radius);
    }
}
