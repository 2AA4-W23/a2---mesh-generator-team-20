package ca.mcmaster.cas.se2aa4.a2.generator.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.Objects;

public class SegmentADT {
    public VertexADT start;
    public VertexADT end;
    public Color color;

    public SegmentADT(VertexADT start, VertexADT end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    public Structs.Segment toSegment(int startI, int endI) {
        return Structs.Segment.newBuilder()
                .setV1Idx(startI)
                .setV2Idx(endI)
                .addProperties(color.toProperty())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SegmentADT that = (SegmentADT) o;

        if (!Objects.equals(start, that.start)) return false;
        if (!Objects.equals(end, that.end)) return false;
        return color.equals(that.color);
    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + color.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SegmentADT{" +
                "start=" + start +
                ", end=" + end +
                ", color=" + color +
                '}';
    }
}
