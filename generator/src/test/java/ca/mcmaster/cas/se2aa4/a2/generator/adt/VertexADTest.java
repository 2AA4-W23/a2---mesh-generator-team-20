package ca.mcmaster.cas.se2aa4.a2.generator.adt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VertexADTest {
    MeshADT mesh = new MeshADT();
    VertexADT vertex = new VertexADT(mesh, 1, 1, 4);
    @Test
    public void toVertexTest(){
        assertNotNull(vertex.toVertex());
    }
    @Test
    public void toStringTest(){
        assertEquals("VertexADT{" +
                "x=" + vertex.x +
                ", y=" + vertex.y +
                '}',vertex.toString());
    }
}
