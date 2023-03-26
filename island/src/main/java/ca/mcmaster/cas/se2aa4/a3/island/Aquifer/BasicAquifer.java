package ca.mcmaster.cas.se2aa4.a3.island.Aquifer;

import ca.mcmaster.cas.se2aa4.a3.island.utils.Coordinate;

public class BasicAquifer implements AquiferProvider {
    private final double centerX;
    private final double centerY;
    private final double radius;
    private final double moisture;

    public BasicAquifer(double width, double height, double moisture) {
        this.centerX = width / 2;
        this.centerY = height / 2;
        this.radius = width / 3 * 2;
        this.moisture = moisture;
    }


    public double getAquifer(Coordinate coordinate) {
        double distance = Math.sqrt(Math.pow(coordinate.x - centerX, 2) + Math.pow(coordinate.y - centerY, 2));
        if (distance > radius) {
            return 0;
        }
        return moisture * (1 - distance / radius);
    }
}
