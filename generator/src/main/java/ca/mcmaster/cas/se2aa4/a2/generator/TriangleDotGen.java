package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.generator.adt.*;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;

public class TriangleDotGen implements DotGen {
    private final int width;
    private final int height;
    private final int squareSize;

    public TriangleDotGen(int width, int height, int squareSize) {
        this.width = width;
        this.height = height;
        this.squareSize = squareSize;
    }

    public Structs.Mesh generateMesh() {
        MeshADT mesh = new MeshADT();

        // Create all the vertices
        for (double x = 0; x <= width; x += squareSize) {
            for (double y = 0; y <= height; y += squareSize) {
                VertexADT vertex = mesh.getVertex(x, y);
                vertex.color = Color.random();
            }
        }

        // Create all the polygons
        for (double x = 0; x < width; x += squareSize) {
            for (double y = 0; y < height; y += squareSize) {
                // build vertex
                // a b
                // d c
                VertexADT a = mesh.getVertex(x, y);
                VertexADT b = mesh.getVertex(x + squareSize, y);
                VertexADT c = mesh.getVertex(x + squareSize, y + squareSize);
                VertexADT d = mesh.getVertex(x, y + squareSize);

                ArrayList<VertexADT> vertices = new ArrayList<>(4);
                vertices.add(a);
                vertices.add(b);
                vertices.add(d);

                // Segment: line change thickness
                SegmentADT segmentAB = mesh.getSegment(a, b);
                SegmentADT segmentBC = mesh.getSegment(b, c);
                segmentAB.thickness = new Thickness(2);
                segmentBC.thickness = new Thickness(2);

                mesh.getPolygon(vertices);

                ArrayList<VertexADT> vertices2 = new ArrayList<>(4);
                vertices2.add(c);
                vertices2.add(b);
                vertices2.add(d);
                mesh.getPolygon(vertices2);

                ArrayList<VertexADT> vertices3 = new ArrayList<>(4);
                vertices3.add(a);
                vertices3.add(b);
                vertices3.add(c);
                mesh.getPolygon(vertices3);

                ArrayList<VertexADT> vertices4 = new ArrayList<>(4);
                vertices4.add(a);
                vertices4.add(d);
                vertices4.add(c);
                mesh.getPolygon(vertices4);
            }
        }

        // color segments
        for (SegmentADT segment : mesh.getSegments()) {
            segment.color = Color.average(segment.getStart().color, segment.getEnd().color);
        }

        return mesh.toMesh();
    }
}