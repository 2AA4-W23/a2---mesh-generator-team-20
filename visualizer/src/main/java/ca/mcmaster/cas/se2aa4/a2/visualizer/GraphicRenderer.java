package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;


public class GraphicRenderer {

    public void render(Mesh mesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);
        List<Vertex> vertices = mesh.getVerticesList();
        for (Vertex v : vertices) {
            double centre_x = v.getX() - (extractThickness(v.getPropertiesList()) / 2.0d);
            double centre_y = v.getY() - (extractThickness(v.getPropertiesList()) / 2.0d);
            canvas.setColor(extractColor(v.getPropertiesList()));
            Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, extractThickness(v.getPropertiesList()), extractThickness(v.getPropertiesList()));
            canvas.fill(point);
        }
        for (Segment segment : mesh.getSegmentsList()) {
            System.out.println(segment);
            Vertex a = vertices.get(segment.getV1Idx());
            Vertex b = vertices.get(segment.getV2Idx());
            canvas.setColor(extractColor(segment.getPropertiesList()));
            // thickness
            stroke = new BasicStroke(extractThickness(segment.getPropertiesList()));
            canvas.setStroke(stroke);
            Line2D line = new Line2D.Double(a.getX(), a.getY(), b.getX(), b.getY());
            canvas.draw(line);
        }
        for (Structs.Polygon polygon : mesh.getPolygonsList()) {
            canvas.setColor(extractColor(polygon.getPropertiesList()));
            List<Integer> segmentIds = polygon.getSegmentIdxsList();
            int[] vertex_x = new int[segmentIds.size()];
            int[] vertex_y = new int[segmentIds.size()];
            List<Vertex> verticesList = new ArrayList<>();
            Vertex pointer;
            if(mesh.getVertices(mesh.getSegments(segmentIds.get(1)).getV1Idx())
                    == mesh.getVertices(mesh.getSegments(segmentIds.get(0)).getV1Idx())
                    || mesh.getVertices(mesh.getSegments(segmentIds.get(1)).getV2Idx())
                    == mesh.getVertices(mesh.getSegments(segmentIds.get(0)).getV1Idx())){
                verticesList.add(mesh.getVertices(mesh.getSegments(segmentIds.get(0)).getV1Idx()));
                pointer = mesh.getVertices(mesh.getSegments(segmentIds.get(0)).getV1Idx());
            } else {
                verticesList.add(mesh.getVertices(mesh.getSegments(segmentIds.get(0)).getV2Idx()));
                pointer = mesh.getVertices(mesh.getSegments(segmentIds.get(0)).getV2Idx());
            }
            for(int i=1; i<segmentIds.size(); i++){
                if(mesh.getVertices(mesh.getSegments(segmentIds.get(i)).getV1Idx()) == pointer){
                    verticesList.add(mesh.getVertices(mesh.getSegments(segmentIds.get(i)).getV2Idx()));
                    pointer = mesh.getVertices(mesh.getSegments(segmentIds.get(i)).getV2Idx());
                }else{
                    verticesList.add(mesh.getVertices(mesh.getSegments(segmentIds.get(i)).getV1Idx()));
                    pointer = mesh.getVertices(mesh.getSegments(segmentIds.get(i)).getV1Idx());
                }
            }
            for (int i = 0; i < verticesList.size(); i++) {
                Vertex v = verticesList.get(i);
                vertex_x[i] = (int) v.getX();
                vertex_y[i] = (int) v.getY();
            }
            canvas.fillPolygon(vertex_x, vertex_y, verticesList.size());
        }
    }

    private Color extractColor(List<Property> properties) {
        String val = null;
        for (Property p : properties) {
            if (p.getKey().equals("rgb_color")) {
                // System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        if (val == null)
            return Color.BLACK;
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);
        return new Color(red, green, blue);
    }

    // extract thickness
    //prop [color: [red,blue,yellow]
    //     [Thickness: [int]]
    private int extractThickness(List<Property> properties) {
        String val = null;
        for (Property p : properties) {
            if (p.getKey().equals("thickness")) {
                // System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        if (val == null) {
            return 1;
        }
        int raw = Integer.parseInt(val);
        return raw;
    }

}
