package ca.mcmaster.cas.se2aa4.a2.generator.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// mesh adt class with an arraylist of vertices and an arraylist of segments, both are private
public class MeshADT {
    private Set<VertexADT> vertices;
    private Set<SegmentADT> segments;

    public MeshADT() {
        this.vertices = new HashSet<>();
        this.segments = new HashSet<>();
    }

    public void addVertex(VertexADT v) {
        this.vertices.add(v);
    }

    public void addSegment(SegmentADT s) {
        this.segments.add(s);
    }

    public Structs.Mesh toMesh() {
        ArrayList<Structs.Vertex> verticesList = new ArrayList<>();
        for (VertexADT vertex : vertices) {
            verticesList.add(vertex.toVertex());
        }

        ArrayList<Structs.Segment> segmentsList = new ArrayList<>();
        for (SegmentADT segment : segments) {
            int startI = findVertexI(verticesList, segment.start);
            int endI = findVertexI(verticesList, segment.end);
            segmentsList.add(segment.toSegment(startI, endI));
        }

        return Structs.Mesh.newBuilder()
                .addAllVertices(verticesList)
                .addAllSegments(segmentsList)
                .build();
    }

    private int findVertexI(List<Structs.Vertex> verticesList, VertexADT vertexADT) {
        for (int i = 0; i < verticesList.size(); i++) {
            Structs.Vertex vertex = verticesList.get(i);
            if (vertex.getX() == vertexADT.x && vertex.getY() == vertexADT.y) {
                return i;
            }
        }
        throw new RuntimeException("not found");
    }

    @Override
    public String toString() {
        return "MeshADT{" +
                "vertices=" + vertices +
                ", segments=" + segments +
                '}';
    }
}