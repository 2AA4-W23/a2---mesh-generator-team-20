package ca.mcmaster.cas.se2aa4.a2.generator.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SegmentADTest {
    MeshADT mesh = new MeshADT();
    VertexADT a = mesh.getVertex(1, 1);
    VertexADT b = mesh.getVertex(2, 1);
    SegmentADT segment = new SegmentADT(a, b, 4);
    Structs.Segment segmentBuilder = segment.toSegment();
    @Test
    public void toSegmentTest(){
        assertNotNull(segmentBuilder);
    }
    @Test
    public void getStartTest(){
        assertEquals(a,segment.getStart());
    }
    @Test
    public void getEndTest(){
        assertEquals(b,segment.getEnd());
    }
    @Test
    public void toStringTest(){
        assertEquals("SegmentADT{" +
                "start=" + segment.getStart() +
                ", end=" + segment.getEnd() +
                '}', segment.toString());
    }
}
