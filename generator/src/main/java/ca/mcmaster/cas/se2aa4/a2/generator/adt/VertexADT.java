package ca.mcmaster.cas.se2aa4.a2.generator.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;

public class VertexADT {
    public double x;
    public double y;
    public Color color;
    public Thickness thickness;
    final ArrayList<SegmentADT> segments = new ArrayList<>();
    final int id;

    VertexADT(double x, double y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public Structs.Vertex toVertex() {
        Structs.Vertex.Builder builder = Structs.Vertex.newBuilder()
                .setX(x)
                .setY(y);
        if (color != null) {
            builder.addProperties(color.toProperty());
        }
        if (thickness != null) {
            builder.addProperties(thickness.toProperty());
        }
        return builder.build();
    }

    @Override
    public String toString() {
        return "VertexADT{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
