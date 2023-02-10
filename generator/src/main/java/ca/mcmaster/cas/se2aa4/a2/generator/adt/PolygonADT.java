package ca.mcmaster.cas.se2aa4.a2.generator.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.HashSet;
import java.util.List;

public class PolygonADT {
    private final List<SegmentADT> segments;
    final int id;

    PolygonADT(List<SegmentADT> segments, int id) {
        this.segments = segments;
        for (SegmentADT segment : segments) {
            segment.polygons.add(this);
        }
        this.id = id;
    }

    public Structs.Polygon toPolygon() {
        Structs.Polygon.Builder builder = Structs.Polygon.newBuilder();
        HashSet<Integer> neighborIds = new HashSet<>();
        for (SegmentADT segment : segments) {
            builder.addSegmentIdxs(segment.id);

            for (PolygonADT neighborPolygon : segment.polygons) {
                if (neighborPolygon != this) {
                    neighborIds.add(neighborPolygon.id);
                }
            }
        }

        builder.addAllNeighborIdxs(neighborIds);

        return builder.build();
    }
}
