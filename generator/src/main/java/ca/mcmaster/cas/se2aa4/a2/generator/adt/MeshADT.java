package ca.mcmaster.cas.se2aa4.a2.generator.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.triangulate.DelaunayTriangulationBuilder;

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
        VertexADT vertex = new VertexADT(x, y, vertices.size());
        vertices.add(vertex);
        return vertex;
    }

    public SegmentADT getSegment(VertexADT start, VertexADT end) {
        for (SegmentADT segment : segments) {
            if ((segment.getStart() == start && segment.getEnd() == end) || (segment.getEnd() == start && segment.getStart() == end)) {
                return segment;
            }
        }
        SegmentADT segment = new SegmentADT(start, end, segments.size());
        segments.add(segment);
        return segment;
    }

    public PolygonADT getPolygon(List<VertexADT> polygonVertices) {
        ArrayList<SegmentADT> polygonSegments = new ArrayList<>(polygonVertices.size());
        for (int i = 1; i < polygonVertices.size(); i++) {
            polygonSegments.add(getSegment(polygonVertices.get(i - 1), polygonVertices.get(i)));
        }
        polygonSegments.add(getSegment(polygonVertices.get(polygonVertices.size() - 1), polygonVertices.get(0)));

        PolygonADT polygon = new PolygonADT(this,polygonSegments, polygonVertices, polygons.size());
        polygons.add(polygon);
        return polygon;
    }

    public PolygonADT findPolygonByCentroid(double x, double y) {
        for (PolygonADT polygon : polygons) {
            if (Math.abs(polygon.centroid.x- x)<0.1 && Math.abs(polygon.centroid.y- y)<0.1) {
                return polygon;
            }
        }
        return null;
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
        DelaunayTriangulationBuilder triangulationBuilder = new DelaunayTriangulationBuilder();
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        for (PolygonADT polygon : polygons) {
            coordinates.add(new Coordinate(polygon.centroid.x, polygon.centroid.y));
        }
        triangulationBuilder.setSites(coordinates);
        GeometryCollection triangles = (GeometryCollection) triangulationBuilder.getTriangles(new GeometryFactory());
        for (int i = 0; i < triangles.getNumGeometries(); i++) {
            Geometry triangle = triangles.getGeometryN(i);
            Coordinate[] triangleCoordinates = triangle.getCoordinates();

            Coordinate a = triangleCoordinates[0];
            Coordinate b = triangleCoordinates[1];
            Coordinate c = triangleCoordinates[2];
            PolygonADT polygonA = findPolygonByCentroid(a.x, a.y);
            PolygonADT polygonB = findPolygonByCentroid(b.x, b.y);
            PolygonADT polygonC = findPolygonByCentroid(c.x, c.y);
            polygonA.neighbours.add(polygonB);
            polygonA.neighbours.add(polygonC);
            polygonB.neighbours.add(polygonA);
            polygonB.neighbours.add(polygonC);
            polygonC.neighbours.add(polygonA);
            polygonC.neighbours.add(polygonB);
        }

        Structs.Mesh.Builder builder = Structs.Mesh.newBuilder();
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