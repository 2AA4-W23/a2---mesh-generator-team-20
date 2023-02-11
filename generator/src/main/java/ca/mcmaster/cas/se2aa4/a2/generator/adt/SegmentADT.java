package ca.mcmaster.cas.se2aa4.a2.generator.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;

public class SegmentADT {
    private VertexADT start;
    private VertexADT end;
    public Color color;
    ArrayList<PolygonADT> polygons = new ArrayList<>();
    final int id;

    SegmentADT(VertexADT start, VertexADT end, int id) {
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
