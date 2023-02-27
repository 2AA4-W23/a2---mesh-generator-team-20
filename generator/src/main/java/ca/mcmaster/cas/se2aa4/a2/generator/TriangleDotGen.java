package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.generator.adt.*;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;

public class TriangleDotGen implements DotGen {
    private final int width;
    private final int height;
    private final int squareSize;
    private final int segmentThickness;
    private final int vertexThickness;

    public TriangleDotGen(int width, int height, int squareSize, int segmentThickness, int vertexThickness) {
        this.width = width;
        this.height = height;
        this.squareSize = squareSize;
        this.segmentThickness = segmentThickness;
        this.vertexThickness = vertexThickness;
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
                a.thickness = new Thickness(vertexThickness);
                b.thickness = new Thickness(vertexThickness);
                c.thickness = new Thickness(vertexThickness);
                d.thickness = new Thickness(vertexThickness);

                generateTriangle(a, b, d, mesh);
                generateTriangle(b, c, d, mesh);
            }
        }

        // color segments
        for (SegmentADT segment : mesh.getSegments()) {
            segment.color = Color.average(segment.start.color, segment.end.color);
        }

        return mesh.toMesh();
    }

    private void generateTriangle(VertexADT a, VertexADT b, VertexADT c, MeshADT mesh) {
        ArrayList<VertexADT> vertices = new ArrayList<>();
        vertices.add(a);
        vertices.add(b);
        vertices.add(c);

        SegmentADT segmentAB = mesh.getSegment(a, b);
        SegmentADT segmentBC = mesh.getSegment(b, c);
        SegmentADT segmentCA = mesh.getSegment(c, a);
        segmentAB.thickness = new Thickness(segmentThickness);
        segmentAB.color = Color.average(a.color, b.color);
        segmentBC.thickness = new Thickness(segmentThickness);
        segmentBC.color = Color.average(b.color, c.color);
        segmentCA.thickness = new Thickness(segmentThickness);
        segmentCA.color = Color.average(c.color, a.color);

        PolygonADT polygonADT = mesh.getPolygon(vertices);
        polygonADT.centroid.thickness = new Thickness(vertexThickness);
    }
}