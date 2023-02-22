package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.generator.adt.*;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

import java.util.ArrayList;

public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int squareSize = 20;

    public Mesh generate() {
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
                vertices.add(c);
                vertices.add(d);

                // Segment: line change thickness
                SegmentADT segmentAB = mesh.getSegment(a, b);
                SegmentADT segmentBC = mesh.getSegment(b, c);
                segmentAB.thickness = new Thickness(25);
                segmentBC.thickness = new Thickness(25);

                mesh.getPolygon(vertices);
            }
        }

        // color segments
        for (SegmentADT segment : mesh.getSegments()) {
            segment.color = Color.average(segment.getStart().color, segment.getEnd().color);
        }

        return mesh.toMesh();
    }
}
