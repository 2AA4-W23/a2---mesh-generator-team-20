package ca.mcmaster.cas.se2aa4.a2.generator.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// mesh adt class with an arraylist of vertices and an arraylist of segments, both are private
public class MeshADT {
    private List<PolygonADT> polygons;

    public MeshADT(List<PolygonADT> polygons) {
        this.polygons = polygons;
    }

    public void toMesh() {
        HashSet<Structs.Vertex> vertices = new HashSet<>();
        for (PolygonADT polygon : polygons) {
            for (VertexADT vertexADT : polygon.getVertices()) {
                vertices.add(vertexADT.toVertex());
            }
        }
        List<Structs.Vertex> vertexList = new ArrayList<>(vertices);

        HashSet<Structs.Segment> segments = new HashSet<>();
        for (PolygonADT polygon : polygons) {
            for (SegmentADT segmentADT : polygon.getSegments()) {
                segments.add(segmentADT.toSegment(
                        findVertex(vertexList, segmentADT.start),
                        findVertex(vertexList, segmentADT.end)
                ));
            }
        }
        List<Structs.Segment> segmentList = new ArrayList<>(segments);



    }

    private int findVertex(List<Structs.Vertex> verticesList, VertexADT vertexADT) {
        for (int i = 0; i < verticesList.size(); i++) {
            Structs.Vertex vertex = verticesList.get(i);
            if (vertex.getX() == vertexADT.x && vertex.getY() == vertexADT.y) {
                return i;
            }
        }
        throw new RuntimeException("not found");
    }
}