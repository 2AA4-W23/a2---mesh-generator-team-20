package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.generator.adt.MeshADT;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.VertexADT;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

import java.util.ArrayList;
import java.util.Random;

public class VoronoiDotGen implements DotGen {
    private final int width;
    private final int height;
    private final int sitesCount;

    private final int relaxations = 10;

    public VoronoiDotGen(int width, int height, int sitesCount) {
        this.width = width;
        this.height = height;
        this.sitesCount = sitesCount;
    }

    @Override
    public Structs.Mesh generateMesh() {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        Random random = new Random(0);
        for (int i = 0; i < sitesCount; i++) {
            double x = random.nextDouble() * width;
            double y = random.nextDouble() * height;
            coordinates.add(new Coordinate(x, y));
        }
        GeometryCollection diagram = null;

        for (int i = 0; i < relaxations; i++) {
            VoronoiDiagramBuilder vdb = new VoronoiDiagramBuilder();

            vdb.setSites(coordinates);

            GeometryFactory gf = new GeometryFactory();

            diagram = (GeometryCollection) vdb.getDiagram(gf);
            Polygon clip = new GeometryFactory().createPolygon(new Coordinate[]{
                    new Coordinate(0, 0),
                    new Coordinate(width, 0),
                    new Coordinate(width, height),
                    new Coordinate(0, height),
                    new Coordinate(0, 0)
            });
            diagram = (GeometryCollection) diagram.intersection(clip);

            ArrayList<Coordinate> newCoordinates = new ArrayList<>();
            for (int j = 0; j < diagram.getNumGeometries(); j++) {
                Polygon polygon = (Polygon) diagram.getGeometryN(j);
                Coordinate centroid = polygon.getCentroid().getCoordinate();
                newCoordinates.add(centroid);
                coordinates = newCoordinates;
            }
        }

        MeshADT mesh = new MeshADT();
        for (int i = 0; i < diagram.getNumGeometries(); i++) {
            Polygon polygon = (Polygon) diagram.getGeometryN(i);
            ArrayList<VertexADT> vertices = new ArrayList<>();
            for (Coordinate coordinate : polygon.getCoordinates()) {
                vertices.add(mesh.getVertex(coordinate.x, coordinate.y));
            }
            System.out.println(vertices);
            mesh.getPolygon(vertices);
        }
        return mesh.toMesh();
    }
}
