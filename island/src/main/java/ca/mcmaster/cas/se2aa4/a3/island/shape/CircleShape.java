package ca.mcmaster.cas.se2aa4.a3.island.shape;

public class CircleShape implements ShapeProvider {
    private final double x;
    private final double y;
    private final double radius;

    public CircleShape(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public boolean contains(double x, double y) {
        return Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2) <= Math.pow(radius, 2);
    }
}
