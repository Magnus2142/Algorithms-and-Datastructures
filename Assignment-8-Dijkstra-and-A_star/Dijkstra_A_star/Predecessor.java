package Dijkstra_A_star;

public class Predecessor {
    int distance;
    Node predecessor;
    static int infinity = 1000000000;
    int heuristicDistance;
    int combinedHeuristicDistance;

    public int findDistance(){
        return distance;
    }

    public Node findPredecessor(){
        return predecessor;
    }

    public Predecessor(){
        distance = infinity;
    }
}
