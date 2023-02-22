package ca.mcmaster.cas.se2aa4.a2.generator.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.HashSet;
import java.util.List;

public class PolygonADT {
    private final MeshADT mesh;
    private final List<SegmentADT> segments;
    private final List<VertexADT> vertices;
    final int id;

    PolygonADT(MeshADT mesh, List<SegmentADT> segments, List<VertexADT> vertices, int id) {
        this.mesh = mesh;
        this.segments = segments;
        this.vertices = vertices;
        for (SegmentADT segment : segments) {
            segment.polygons.add(this);
        }
        this.id = id;
    }

    // method to calculate centroid of the polygon
    VertexADT getCentroid() {
        double x = 0;
        double y = 0;
        for (VertexADT vertex : vertices) {
            x += vertex.x;
            y += vertex.y;
        }
        return mesh.getVertex(x / segments.size(), y / segments.size());
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

        builder.setCentroidIdx(getCentroid().id);

        return builder.build();
    }
}
