package ca.mcmaster.cas.se2aa4.a2.generator.adt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

// polygon adt class with an arraylist of vertices, which is private
public class PolygonADT {
    private final List<VertexADT> vertices;
    private final List<SegmentADT> segments;

    public PolygonADT(List<VertexADT> vertices) {
        this.vertices = vertices;
        segments = new ArrayList<>(vertices.size());

        for (int i = 1; i < vertices.size(); i++) {
            segments.add(new SegmentADT(
                    vertices.get(i - 1),
                    vertices.get(i),
                    null
            ));
        }
        segments.add(new SegmentADT(
                vertices.get(vertices.size() - 1),
                vertices.get(0),
                null
        ));
    }

    public SegmentADT getSegment(int startVertexI) {
        return segments.get(startVertexI);
    }

    public List<SegmentADT> getSegments() {
        return Collections.unmodifiableList(segments);
    }

    public List<VertexADT> getVertices() {
        return Collections.unmodifiableList(vertices);
    }
}
