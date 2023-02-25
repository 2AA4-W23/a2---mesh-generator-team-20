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
    private static final Option IRREGULAR = new Option("g", "grid",false, "create an irregular mesh");

    private static void printHelp(Options options){
        HelpFormatter formatter = new HelpFormatter();
        PrintWriter pw = new PrintWriter(System.out);
        pw.println("MeshCreator"+Math.class.getPackage().getImplementationVersion());
        pw.println();
        formatter.printUsage(pw, 100, "java -jar MeshCreator.jar [options]");
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

            if(cmd.getArgList().size() < 2){
                printHelp(options);
                System.exit(-1);
            }


            if(cmd.hasOption(GRID.getLongOpt())){
                DotGen generator = new SquareDotGen(500, 500, 100);
                Mesh myMesh = generator.generateMesh();
                MeshFactory factory = new MeshFactory();
                factory.write(myMesh, args[0]);
            } else if (cmd.hasOption(IRREGULAR.getLongOpt())) {
                DotGen generator = new VoronoiDotGen(500, 500, 100);
                Mesh myMesh = generator.generateMesh();
                MeshFactory factory = new MeshFactory();
                factory.write(myMesh, args[0]);
            } else {
                printHelp(options);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

//        DotGen generator = new VoronoiDotGen(500, 500, 100);
//        Mesh myMesh = generator.generateMesh();
//        MeshFactory factory = new MeshFactory();
//        factory.write(myMesh, args[0]);
    }

}
