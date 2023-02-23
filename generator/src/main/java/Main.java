import ca.mcmaster.cas.se2aa4.a2.generator.DotGen;
import ca.mcmaster.cas.se2aa4.a2.generator.VoronoiDotGen;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        DotGen generator = new VoronoiDotGen(500, 500, 100);
        Mesh myMesh = generator.generateMesh();
        MeshFactory factory = new MeshFactory();
        factory.write(myMesh, args[0]);
    }

}
