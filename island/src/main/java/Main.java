import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.IslandGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.color.ColorProvider;
import ca.mcmaster.cas.se2aa4.a3.island.color.NormalIslandColorProvider;
import ca.mcmaster.cas.se2aa4.a3.island.elevation.MountainElevation;
import ca.mcmaster.cas.se2aa4.a3.island.shape.CircleShape;
import org.apache.commons.cli.Option;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.cli.*;

public class Main {
    private static final Option INPUTMESH = new Option("i", "inputmesh", false, "create a grid mesh");
    private static final Option LAGOONMESH = new Option("o", "lagoonmesh", false, "create an irregular mesh");
    private static final Option LAGOON = new Option("m", "lagoon", false, "create an triangle mesh");

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

        Structs.Mesh mesh = new MeshFactory().read("island/in.mesh");
        double width = Double.MIN_VALUE;
        double height = Double.MIN_VALUE;
        for (Structs.Vertex v : mesh.getVerticesList()) {
            width = (Double.compare(width, v.getX()) < 0 ? v.getX() : width);
            height = (Double.compare(height, v.getY()) < 0 ? v.getY() : height);
        }
        ColorProvider colorProvider = new NormalIslandColorProvider(
                new MountainElevation(width, height, 1),
                new CircleShape(width, height, width / 4)
        );
        IslandGenerator islandGenerator = new IslandGenerator(colorProvider);
        mesh = islandGenerator.generate(mesh);
        mesh.writeTo(new FileOutputStream("island/out.mesh"));
    }
}
