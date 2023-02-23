package ca.mcmaster.cas.se2aa4.a2.generator.adt;


import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


public class MeshADTest {
    @Test
    public void getVertexTest(){
        MeshADT vertex = new MeshADT();
        VertexADT expected = vertex.getVertex(2,6);
        assertEquals(expected.x, 2);
        assertEquals(expected.y, 6);
    }
    @Test
    public void getSegmentTest(){
        VertexADT vertexStart = new VertexADT(new MeshADT(),1,2,3);
        VertexADT vertexEnd = new VertexADT(new MeshADT(),4,5,6);
        MeshADT segment = new MeshADT();
        SegmentADT expected = segment.getSegment(vertexStart, vertexEnd);
        assertEquals(expected.getStart(), vertexStart);
        assertEquals(expected.getEnd(), vertexEnd);
    }
    @Test
    public void getPolygonTest(){
        MeshADT mesh = new MeshADT();
        VertexADT a = mesh.getVertex(1, 2);
        VertexADT b = mesh.getVertex(3, 4);
        VertexADT c = mesh.getVertex(5, 6);
        ArrayList<VertexADT> polygonVertices = new ArrayList<>(3);
        polygonVertices.add(a);
        polygonVertices.add(b);
        polygonVertices.add(c);
        PolygonADT expected = mesh.getPolygon(polygonVertices);
        assertEquals(expected.getVertices(), polygonVertices);
    }
    @Test
    public void toMesh(){

    }
}
