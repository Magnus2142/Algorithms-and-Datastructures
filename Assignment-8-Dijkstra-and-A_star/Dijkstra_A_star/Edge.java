package Dijkstra_A_star;

public class Edge {

    Edge next;
    Node to;

    public Edge(Node to, Edge next){
        this.to = to;
        this.next = next;
    }

}
