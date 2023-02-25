import ca.mcmaster.cas.se2aa4.a2.generator.DotGen;
import ca.mcmaster.cas.se2aa4.a2.generator.SquareDotGen;
import ca.mcmaster.cas.se2aa4.a2.generator.VoronoiDotGen;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    private static final Option GRID = new Option("g", "grid",false, "create a grid mesh");
    private static final Option IRREGULAR = new Option("i", "irregular",false, "create an irregular mesh");

    private static void printHelp(Options options){
        HelpFormatter formatter = new HelpFormatter();
        PrintWriter pw = new PrintWriter(System.out);
        pw.println("MeshCreator"+Math.class.getPackage().getImplementationVersion());
        pw.println();
        formatter.printUsage(pw, 100, "mvn compile && mvn -q -e exec:java -Dexec.args=\"sample.mesh [options] width height squareSize\"");
        formatter.printOptions(pw, 100, options, 2,5);
        pw.close();
    }

    public static void main(String[] args) throws IOException, ParseException {
        Options options = new Options();
        options.addOption(GRID);
        options.addOption(IRREGULAR);

        CommandLineParser parser = new DefaultParser();

        try{
            CommandLine cmd = parser.parse(options, args);

            System.out.println(cmd.getArgList());
            if(cmd.getArgList().size() < 1){
                printHelp(options);
                System.exit(-1);
            }

            int width = 0;
            int height = 0;
            int squareSize = 0;
            try{
                width = Integer.parseInt(cmd.getArgList().get(1));
                height = Integer.parseInt(cmd.getArgList().get(2));
                squareSize = Integer.parseInt(cmd.getArgList().get(3));
            }catch (Exception q){
                printHelp(options);
                q.printStackTrace();
                System.exit(-1);
            }

            if(cmd.hasOption("-g")){
                DotGen generator = new SquareDotGen(width, height, squareSize);
                Mesh myMesh = generator.generateMesh();
                MeshFactory factory = new MeshFactory();
                factory.write(myMesh, args[0]);
            } else if (cmd.hasOption("-i")) {
                DotGen generator = new VoronoiDotGen(width, height, squareSize);
                Mesh myMesh = generator.generateMesh();
                MeshFactory factory = new MeshFactory();
                factory.write(myMesh, args[0]);
            } else {
                System.out.println("c");
                printHelp(options);
            }
        } catch(ParseException e){
            e.printStackTrace();
        }

    }

}
