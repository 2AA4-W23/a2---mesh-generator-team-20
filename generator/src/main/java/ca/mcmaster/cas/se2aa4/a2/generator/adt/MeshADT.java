package ca.mcmaster.cas.se2aa4.a2.generator.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.Collections;
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
        VertexADT vertex = new VertexADT(this, x, y, vertices.size());
        vertices.add(vertex);
        return vertex;
    }

    public SegmentADT getSegment(VertexADT start, VertexADT end) {
        for (SegmentADT segment : segments) {
            if ((segment.getStart() == start && segment.getEnd() == end) || (segment.getEnd() == start && segment.getStart() == end)) {
                return segment;
            }
        }
        SegmentADT segment = new SegmentADT(
                this, start,
                end,
                segments.size()
        );
        segments.add(segment);
        return segment;
    }

    public PolygonADT getPolygon(List<VertexADT> polygonVertices) {
        ArrayList<SegmentADT> polygonSegments = new ArrayList<>(polygonVertices.size());
        for (int i = 1; i < polygonVertices.size(); i++) {
            polygonSegments.add(getSegment(polygonVertices.get(i - 1), polygonVertices.get(i)));
        }
        polygonSegments.add(getSegment(polygonVertices.get(polygonVertices.size() - 1), polygonVertices.get(0)));

        PolygonADT polygon = new PolygonADT(this, polygonSegments, polygonVertices, polygons.size());
        polygons.add(polygon);
        return polygon;
    }

    public List<VertexADT> getVertices() {
        return Collections.unmodifiableList(vertices);
    }

    public List<SegmentADT> getSegments() {
        return Collections.unmodifiableList(segments);
    }

    public List<PolygonADT> getPolygons() {
        return Collections.unmodifiableList(polygons);
    }

    public Structs.Mesh toMesh() {
        Structs.Mesh.Builder builder = Structs.Mesh.newBuilder();
        for (PolygonADT polygonADT : polygons) {
            polygonADT.getCentroid();
        }
        for (VertexADT vertexADT : vertices) {
            builder.addVertices(vertexADT.toVertex());
        }
        for (SegmentADT segmentADT : segments) {
            builder.addSegments(segmentADT.toSegment());
        }
        for (PolygonADT polygonADT : polygons) {
            builder.addPolygons(polygonADT.toPolygon());
        }
        return builder.build();
    }
}