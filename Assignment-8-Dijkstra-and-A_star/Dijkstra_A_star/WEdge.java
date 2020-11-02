package Dijkstra_A_star;

public class WEdge extends Edge{
    int weight;
    int lengthInM;
    int speedLimit;


    public WEdge(Node to, Edge next, int weight, int lengthInM, int speedLimit) {
        super(to, next);
        this.weight = weight;
        this.lengthInM = lengthInM;
        this.speedLimit = speedLimit;
    }
}
