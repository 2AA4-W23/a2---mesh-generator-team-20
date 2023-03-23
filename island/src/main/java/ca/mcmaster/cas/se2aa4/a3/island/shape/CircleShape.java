package ca.mcmaster.cas.se2aa4.a3.island.shape;

public class CircleShape implements Shape {
    private final int x;
    private final int y;
    private final int radius;

    public CircleShape(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public boolean contains(int x, int y) {
        return Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2) <= Math.pow(radius, 2);
    }
}
