package ca.mcmaster.cas.se2aa4.a3.island.shape;

public class CircleShape implements ShapeProvider {
    private final double x;
    private final double y;
    private final double radius;

    public CircleShape(double width, double height, double radius) {
        this.x = width/2;
        this.y = height/2;
        this.radius = radius;
    }

    @Override
    public boolean contains(double x, double y) {
        return Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2) <= Math.pow(radius, 2);
    }
}
