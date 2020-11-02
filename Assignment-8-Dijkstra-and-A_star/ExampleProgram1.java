import Dijkstra_A_star.Graph;
import Dijkstra_A_star.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * This example program finds the path from start node to goal node, comparing the A* method and
 * Dijkstra method. In the end, a long list of coordinates gets printed out which can be plotted into
 * a map to show the path. A good website to plot many coordinates at once is Mapcustomizer.com
 */
public class ExampleProgram1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReaderRoads = new BufferedReader(new FileReader("src/files/kanter.txt"));
        BufferedReader bufferedReaderCoordinates = new BufferedReader(new FileReader("src/files/noder.txt"));
        BufferedReader bufferedReaderPointsOfInterest = new BufferedReader(new FileReader("src/files/interessepkt.txt"));

        Graph graph = new Graph();
        graph.new_WGraph(bufferedReaderCoordinates, bufferedReaderPointsOfInterest, bufferedReaderRoads);

        System.out.println("\nFra Trondheim til Helsinki\n");

        Node trondheimVaernes = graph.getNode()[5249833];
        Node trondheim = graph.getNode()[2399829];
        Node oslo = graph.getNode()[2353304];
        Node helsinki = graph.getNode()[1221382];
        Node stockholm = graph.getNode()[5523125];
        Node korog = graph.getNode()[6013683];
        Node Gjemnes = graph.getNode()[6225195];

        long startTime = System.nanoTime();
        Node routeNode = graph.Astar(trondheim, helsinki);
        long endTime = System.nanoTime();

        System.out.println("Tid med A* = " + ((endTime-startTime)/1000000) + " ms");
        System.out.println("Antall noder plukket ut av køen: " + graph.getNodePolledCounter());
        graph.printRoadInfo(routeNode);

        System.out.println();

        long startTime2 = System.nanoTime();
        Node routeNode2 = graph.dijkstra(trondheim, helsinki);
        long endTime2 = System.nanoTime();

        System.out.println("Tid med Dijkstra = " + (endTime2-startTime2)/1000000 + " ms");
        System.out.println("Antall noder plukket ut av køen: " + graph.getNodePolledCounter());
        graph.printRoadInfo(routeNode2);

        graph.printRoad(routeNode);
    }
}
