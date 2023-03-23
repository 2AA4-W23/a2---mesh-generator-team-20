import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.apache.commons.cli.Option;

import java.awt.*;
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

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.getArgList().size() < 1) {
                printHelp(options);
                System.exit(-1);
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
