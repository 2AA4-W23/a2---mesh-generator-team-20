package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.color.Color;
import ca.mcmaster.cas.se2aa4.a3.island.color.ColorProvider;

import java.util.ArrayList;
import java.util.List;

public class IslandGenerator {
    private final ColorProvider colorProvider;

    public IslandGenerator(ColorProvider colorProvider) {
        this.colorProvider = colorProvider;
    }

    public Structs.Mesh generate(Structs.Mesh mesh) {
        Structs.Mesh.Builder builder = Structs.Mesh.newBuilder();
        List<Structs.Vertex> vertices = mesh.getVerticesList();
        builder.addAllVertices(vertices);
        builder.addAllSegments(mesh.getSegmentsList());
        List<Structs.Polygon> polygons = new ArrayList<>();
        for (Structs.Polygon polygon : mesh.getPolygonsList()) {
            int centroidId = polygon.getCentroidIdx();
            Structs.Vertex centroid = vertices.get(centroidId);
            Color color = colorProvider.getColor(centroid.getX(), centroid.getY());
            Structs.Polygon.Builder polygonBuilder = Structs.Polygon.newBuilder(polygon);
            polygonBuilder.clearProperties();
            polygonBuilder.addProperties(color.toProperty());
            polygons.add(polygonBuilder.build());
        }
        builder.addAllPolygons(polygons);
        return builder.build();
    }
}
