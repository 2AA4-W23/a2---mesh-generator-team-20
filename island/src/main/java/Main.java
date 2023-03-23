import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.IslandGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.color.ColorProvider;
import ca.mcmaster.cas.se2aa4.a3.island.color.NormalIslandColorProvider;
import ca.mcmaster.cas.se2aa4.a3.island.elevation.ElevationProvider;
import ca.mcmaster.cas.se2aa4.a3.island.elevation.MountainElevation;
import ca.mcmaster.cas.se2aa4.a3.island.lake.CircleLakeProvider;
import ca.mcmaster.cas.se2aa4.a3.island.lake.LakeProvider;
import ca.mcmaster.cas.se2aa4.a3.island.shape.CircleShape;
import ca.mcmaster.cas.se2aa4.a3.island.shape.ShapeProvider;
import org.apache.commons.cli.Option;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.cli.*;

public class Main {
    private static final Option INPUTMESH = new Option("i", "inputMesh", false, "");
    private static final Option LAGOONMESH = new Option("o", "lagoonMesh", false, "");
    private static final Option LAGOON = new Option("m", "lagoon", false, "");
    private static final Option SHAPE = new Option("s", "shape", false, "");
    private static final Option ELEVATION = new Option("e", "altitude", false, "");
    private static final Option LAKES = new Option("l", "lakes", false, "");
    private static final Option RIVERS = new Option("r", "rivers", false, "");
    //    private static final Option RIVERFLOW = new Option("", "altitude", false, "");
    private static final Option AQUIFERS = new Option("a", "aquifers", false, "");
    private static final Option SOILABSORPTION = new Option("s", "soil", false, "");
    //    private static final Option BIOMES = new Option("e", "altitude", false, "");
    private static final Option WHITTAKERDIAGRAMS = new Option("w", "biomes", false, "");
    private static final Option REPRODUCIBILITY = new Option("p", "seed", false, "");

    private static void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        PrintWriter pw = new PrintWriter(System.out);
        formatter.printUsage(pw, 100, "java -jar island.jar -i input.mesh -o lagoon.mesh --mode lagoon");
        formatter.printOptions(pw, 100, options, 2, 5);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        //java -jar island.jar -i input.mesh -o lagoon.mesh --mode lagoon
        Options options = new Options();
        options.addOption(INPUTMESH);
        options.addOption(LAGOONMESH);
        options.addOption(LAGOON);
        options.addOption(SHAPE);
        options.addOption(ELEVATION);
        options.addOption(LAKES);
        options.addOption(RIVERS);
        options.addOption(AQUIFERS);
        options.addOption(SOILABSORPTION);
        options.addOption(WHITTAKERDIAGRAMS);
        options.addOption(REPRODUCIBILITY);

        CommandLineParser parser = new DefaultParser();

//        try {
//            CommandLine cmd = parser.parse(options, args);
//
//            if (cmd.getArgList().size() < 1) {
//                printHelp(options);
//                System.exit(-1);
//            }
//
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        Structs.Mesh mesh = new MeshFactory().read("generator/sample.mesh");
        double width = Double.MIN_VALUE;
        double height = Double.MIN_VALUE;
        for (Structs.Vertex v : mesh.getVerticesList()) {
            width = (Double.compare(width, v.getX()) < 0 ? v.getX() : width);
            height = (Double.compare(height, v.getY()) < 0 ? v.getY() : height);
        }
        ElevationProvider elevationProvider = new MountainElevation(width, height, 1);
        ShapeProvider shapeProvider = new CircleShape(width, height, width / 4);
        LakeProvider lakeProvider = new CircleLakeProvider(width, height, elevationProvider, shapeProvider, 4);
        ColorProvider colorProvider = new NormalIslandColorProvider(
                elevationProvider,
                shapeProvider,
                lakeProvider
        );
        IslandGenerator islandGenerator = new IslandGenerator(colorProvider);
        mesh = islandGenerator.generate(mesh);
        mesh.writeTo(new FileOutputStream("island/out.mesh"));
    }
}
