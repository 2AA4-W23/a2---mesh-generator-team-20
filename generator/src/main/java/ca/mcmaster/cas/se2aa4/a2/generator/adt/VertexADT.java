package ca.mcmaster.cas.se2aa4.a2.generator.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;

public class VertexADT {
    private final MeshADT mesh;
    public double x;
    public double y;
    public Color color;
    final ArrayList<SegmentADT> segments = new ArrayList<>();
    final int id;

    VertexADT(MeshADT mesh, double x, double y, int id) {
        this.mesh = mesh;
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
