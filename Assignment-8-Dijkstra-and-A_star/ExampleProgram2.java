import Dijkstra_A_star.Graph;
import Dijkstra_A_star.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


/**
 * This exampleprogram finds all the closest gas stations near trondheim vaernes and all the closest
 * charging stations near Røros Hotell
 */

public class ExampleProgram2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReaderRoads = new BufferedReader(new FileReader("src/files/kanter.txt"));
        BufferedReader bufferedReaderCoordinates = new BufferedReader(new FileReader("src/files/noder.txt"));
        BufferedReader bufferedReaderPointsOfInterest = new BufferedReader(new FileReader("src/files/interessepkt.txt"));

        Graph graph = new Graph();
        graph.new_WGraph(bufferedReaderCoordinates, bufferedReaderPointsOfInterest, bufferedReaderRoads);


        Node trondheimVaernes = graph.getNode()[5249833];
        Node rorosHotell = graph.getNode()[1117256];

        Node[] closestNodesGasStation = graph.dijkstraFindClosest(trondheimVaernes, 2, 10);
        Node[] closestNodesChargingStation = graph.dijkstraFindClosest(rorosHotell, 4, 10);

        System.out.println("Koordinater til de 10 nærmeste bensinstasjonenen rundt Værnes flyplass: ");
        Arrays.stream(closestNodesGasStation).forEach(Node::printCoordinates);

        System.out.println("\nKoordinater til de 10 nærmeste ladestasjonene rundt Røro hotell: ");
        Arrays.stream(closestNodesChargingStation).forEach(Node::printCoordinates);

    }
}
