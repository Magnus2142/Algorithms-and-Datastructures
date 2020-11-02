package Dijkstra_A_star;

public class Node {

    Edge edge1;
    int nodeNumber;
    Object d;

    double latitude;
    double longitude;

    int code;
    String name;

    public Node(int nodeNumber){
        this.nodeNumber = nodeNumber;
    }

    public void printCoordinates(){
        System.out.println(latitude*(180/Math.PI) + "," + longitude*(180/Math.PI));
    }
}
