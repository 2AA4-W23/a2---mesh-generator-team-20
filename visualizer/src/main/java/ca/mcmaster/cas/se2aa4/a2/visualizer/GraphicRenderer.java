package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;


public class GraphicRenderer {
    private static final Logger logger = LogManager.getLogger(GraphicRenderer.class);

    public void render(Mesh mesh, Graphics2D canvas) {
        boolean isDebug = System.getProperty("env").equals("debug");
        if (isDebug) {
            logger.info("Debug mode detected");
        }

        List<Vertex> vertices = mesh.getVerticesList();
        // draw all vertices
        for (Vertex v : vertices) {
            logger.trace("drawing vertex: {}", v);
            double centre_x = v.getX() - (extractThickness(v.getPropertiesList()) / 2.0d);
            double centre_y = v.getY() - (extractThickness(v.getPropertiesList()) / 2.0d);

            // set centroid color to red if in debug mode
            if (isDebug && extractIsCentroid(v.getPropertiesList())) {
                canvas.setColor(new Color(255, 0, 0));
            } else {
                canvas.setColor(extractColor(v.getPropertiesList()));
            }

            Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, extractThickness(v.getPropertiesList()), extractThickness(v.getPropertiesList()));
            canvas.fill(point);
        }

        // draw all segments
        for (Segment segment : mesh.getSegmentsList()) {
            logger.trace("drawing segment: {}", segment);
            Vertex a = vertices.get(segment.getV1Idx());
            Vertex b = vertices.get(segment.getV2Idx());

            // set segment color to black if in debug mode
            if (isDebug) {
                canvas.setColor(new Color(0, 0, 0));
            } else {
                canvas.setColor(extractColor(segment.getPropertiesList()));
            }

            // thickness
            Stroke stroke = new BasicStroke(extractThickness(segment.getPropertiesList()));
            canvas.setStroke(stroke);
            Line2D line = new Line2D.Double(a.getX(), a.getY(), b.getX(), b.getY());
            canvas.draw(line);
        }
    }

    private Color extractColor(List<Property> properties) {
        String val = getProperty(properties, "rgb_color");
        if (val == null)
            return Color.BLACK;
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);
        return new Color(red, green, blue);
    }

    // extract thickness
    // prop [color: [red,blue,yellow]
    //     [Thickness: [int]]
    private int extractThickness(List<Property> properties) {
        String val = getProperty(properties, "thickness");
        if (val == null) {
            return 1;
        }
        return Integer.parseInt(val);
    }

    // extract centroid; [True]
    private boolean extractIsCentroid(List<Property> properties) {
        String val = getProperty(properties, "centroid");
        if (val == null) {
            return false;
        }

        return val.equals("true");
    }

    private String getProperty(List<Property> properties, String key) {
        for (Property p : properties) {
            if (p.getKey().equals(key)) {
                return p.getValue();
            }
        }
        return null;
    }
}
