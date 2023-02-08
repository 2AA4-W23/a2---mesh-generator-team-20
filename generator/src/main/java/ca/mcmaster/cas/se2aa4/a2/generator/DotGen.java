package ca.mcmaster.cas.se2aa4.a2.generator;

import java.io.IOException;
import java.util.*;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

    public Mesh generate() {
        Set<Vertex> vertices = new HashSet<>();
        // Create all the vertices
        for (int x = 0; x < width; x += square_size) {
            for (int y = 0; y < height; y += square_size) {
                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y).build());
                vertices.add(Vertex.newBuilder().setX((double) x + square_size).setY((double) y).build());
                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y + square_size).build());
                vertices.add(Vertex.newBuilder().setX((double) x + square_size).setY((double) y + square_size).build());
            }
        }

        // Distribute colors randomly. Vertices are immutable, need to enrich them
        Set<Vertex> verticesWithColors = new HashSet<>();
        Random random = new Random();
        for (Vertex oldV : vertices) {
            int red = random.nextInt(255);
            int green = random.nextInt(255);
            int blue = random.nextInt(255);
            String colorCode = red + "," + green + "," + blue;
            Property colorProperty = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
            Vertex colored = Vertex.newBuilder(oldV).addProperties(colorProperty).build();
            verticesWithColors.add(colored);
        }

        List<Vertex> verticesList = new ArrayList<>(verticesWithColors);
        HashSet<Segment> segments = new HashSet<>();
        for (int x = 0; x < width; x += square_size) {
            for (int y = 0; y < height; y += square_size) {
                int a = findVertex(verticesList, x, y);
                Vertex aVertex = verticesList.get(a);
                int b = findVertex(verticesList, x + square_size, y);
                Vertex bVertex = verticesList.get(b);
                int c = findVertex(verticesList, x, y + square_size);
                Vertex cVertex = verticesList.get(c);
                int d = findVertex(verticesList, x + square_size, y + square_size);
                Vertex dVertex = verticesList.get(d);

                segments.add(Segment.newBuilder()
                        .setV1Idx(a)
                        .setV2Idx(b)
                        .addProperties(Property.newBuilder().setKey("rgb_color").setValue(getAverageColor(aVertex, bVertex)).build())
                        .build());
                segments.add(Segment.newBuilder()
                        .setV1Idx(b)
                        .setV2Idx(d)
                        .addProperties(Property.newBuilder().setKey("rgb_color").setValue(getAverageColor(bVertex, dVertex)).build())
                        .build());
                segments.add(Segment.newBuilder()
                        .setV1Idx(d)
                        .setV2Idx(c)
                        .addProperties(Property.newBuilder().setKey("rgb_color").setValue(getAverageColor(dVertex, cVertex)).build())
                        .build());
                segments.add(Segment.newBuilder()
                        .setV1Idx(c)
                        .setV2Idx(a)
                        .addProperties(Property.newBuilder().setKey("rgb_color").setValue(getAverageColor(cVertex, aVertex)).build())
                        .build());
            }
        }

        return Mesh.newBuilder()
                .addAllVertices(verticesList)
                .addAllSegments(segments)
                .build();
    }

    private int findVertex(List<Vertex> verticesList, double x, double y) {
        for (int i = 0; i < verticesList.size(); i++) {
            Vertex vertex = verticesList.get(i);
            if (vertex.getX() == x && vertex.getY() == y) {
                return i;
            }
        }
        throw new RuntimeException("fuck");
    }

    private String getAverageColor(Vertex aV, Vertex bV) {
        String aColor = aV.getProperties(0).getValue();
        String bColor = bV.getProperties(0).getValue();

        String[] aColorList = aColor.split(",");
        String[] bColorList = bColor.split(",");

        int r = (Integer.parseInt(aColorList[0]) + Integer.parseInt(bColorList[0])) / 2;
        int g = (Integer.parseInt(aColorList[1]) + Integer.parseInt(bColorList[1])) / 2;
        int b = (Integer.parseInt(aColorList[2]) + Integer.parseInt(bColorList[2])) / 2;

        return r + "," + g + "," + b;
    }

}
