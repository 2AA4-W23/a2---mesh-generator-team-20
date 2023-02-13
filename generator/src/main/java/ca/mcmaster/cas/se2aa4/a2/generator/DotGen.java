package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.generator.adt.Color;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.MeshADT;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.SegmentADT;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.VertexADT;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

import java.util.ArrayList;

public class DotGen {
    private final int width;
    private final int height;

    public DotGen(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Mesh generateSquareMesh(int squareSize) {
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
                ArrayList<VertexADT> vertices = new ArrayList<>(4);
                vertices.add(mesh.getVertex(x, y));
                vertices.add(mesh.getVertex(x + squareSize, y));
                vertices.add(mesh.getVertex(x + squareSize, y + squareSize));
                vertices.add(mesh.getVertex(x, y + squareSize));
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
