package ca.mcmaster.cas.se2aa4.a2.generator.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;

public class SegmentADT {
    private final MeshADT mesh;
    private final VertexADT start;
    private final VertexADT end;
    public Color color;
    // field of thickness
    public Thickness thickness;
    final ArrayList<PolygonADT> polygons = new ArrayList<>();
    final int id;

    SegmentADT(MeshADT mesh, VertexADT start, VertexADT end, int id) {
        this.mesh = mesh;
        this.start = start;
        start.segments.add(this);
        this.end = end;
        end.segments.add(this);
        this.id = id;
    }

    public Structs.Segment toSegment() {
        Structs.Segment.Builder builder = Structs.Segment.newBuilder()
                .setV1Idx(start.id)
                .setV2Idx(end.id);
        if (color != null) {
            builder.addProperties(color.toProperty());
        }
        // tell io, or io will not store in .mesh
        if (thickness != null) {
            builder.addProperties(thickness.toProperty());
        }
        return builder.build();
    }

    public VertexADT getStart() {
        return start;
    }

    public VertexADT getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "SegmentADT{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
