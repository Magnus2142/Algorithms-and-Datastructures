import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExampleProgram {

    public static void main(String[] args){

        BufferedReader bufferedReader;
        Graph graph;

        try {
            graph = new Graph();
            bufferedReader = new BufferedReader(new FileReader("src/L7g1"));
            graph.createUGraph(bufferedReader, "L7g1");
            graph.SCC();

            graph = new Graph();
            bufferedReader = new BufferedReader(new FileReader("src/L7g2"));
            graph.createUGraph(bufferedReader, "L7g2");
            graph.SCC();

            graph = new Graph();
            bufferedReader = new BufferedReader(new FileReader("src/L7g5"));
            graph.createUGraph(bufferedReader, "L7g5");
            graph.SCC();

            graph = new Graph();
            bufferedReader = new BufferedReader(new FileReader("src/L7g6"));
            graph.createUGraph(bufferedReader, "L7g6");
            graph.SCC();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
