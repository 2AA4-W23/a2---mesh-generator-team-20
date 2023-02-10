package ca.mcmaster.cas.se2aa4.a2.generator.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.Objects;

public class VertexADT {
    public double x;
    public double y;
    public Color color;

    public VertexADT(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Structs.Vertex toVertex() {
        return Structs.Vertex.newBuilder()
                .setX(x)
                .setY(y)
                .addProperties(color.toProperty())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VertexADT vertexADT = (VertexADT) o;

        if (Double.compare(vertexADT.x, x) != 0) return false;
        if (Double.compare(vertexADT.y, y) != 0) return false;
        return color.equals(vertexADT.color);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + color.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "VertexADT{" +
                "x=" + x +
                ", y=" + y +
                ", color=" + color +
                '}';
    }
}
