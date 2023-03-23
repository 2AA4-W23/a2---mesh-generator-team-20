package ca.mcmaster.cas.se2aa4.a3.island.elevation;

public class MountainElevation implements ElevationProvider {
    private final double centerX;
    private final double centerY;
    private final double radius;
    private final double height;

    MountainElevation(double centerX, double centerY, double radius, double height) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.height = height;
    }

    public double getElevation(double x, double y) {
        double distance = Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
        if (distance > radius) {
            return 0;
        }
        return height * (1 - distance / radius);
    }
}
