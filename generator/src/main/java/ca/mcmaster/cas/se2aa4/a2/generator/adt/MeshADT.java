package ca.mcmaster.cas.se2aa4.a2.generator.adt;

import java.util.ArrayList;
import java.util.List;

public class MeshADT {
    private final ArrayList<VertexADT> vertices = new ArrayList<>();
    private final ArrayList<SegmentADT> segments = new ArrayList<>();
    private final ArrayList<PolygonADT> polygons = new ArrayList<>();

    public MeshADT() {
    }

    public VertexADT getVertex(double x, double y) {
        for (VertexADT vertex : vertices) {
            if (vertex.x == x && vertex.y == y) {
                return vertex;
            }
        }
        VertexADT vertex = new VertexADT(x, y, vertices.size());
        vertices.add(vertex);
        return vertex;
    }

    public SegmentADT getSegment(VertexADT start, VertexADT end) {
        for (SegmentADT segment : segments) {
            if ((segment.start == start && segment.end == end) || (segment.end == start && segment.start == end)) {
                return segment;
            }
        }
        SegmentADT segment = new SegmentADT(
                start,
                end,
                segments.size()
        );
        segments.add(segment);
        return segment;
    }

    public PolygonADT getPolygon(List<VertexADT> polygonVertices) {
        ArrayList<SegmentADT> polygonSegments = new ArrayList<>(polygonVertices.size());
        for (int i = 0; i < polygonVertices.size(); i++) {
            polygonSegments.add(getSegment(polygonVertices.get(i), polygonVertices.get(i + 1)));
        }
        polygonSegments.add(getSegment(polygonVertices.get(polygonVertices.size() - 1), polygonVertices.get(0)));

        PolygonADT polygon = new PolygonADT(polygonSegments, polygons.size());
        polygons.add(polygon);
        return polygon;
    }
}