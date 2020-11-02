package Dijkstra_A_star;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Graph {

    int NODE_AMOUNT;
    int EDGE_AMOUNT;
    Node[] node;

    int nodePolledCounter = 0;

    public void new_WGraph(BufferedReader brCoordinates, BufferedReader brPointsOfInterest, BufferedReader brRoads) throws IOException {
        readCoordinates(brCoordinates);
        readPointsOfInterest(brPointsOfInterest);

        StringTokenizer st = new StringTokenizer(brRoads.readLine());

        EDGE_AMOUNT = Integer.parseInt(st.nextToken());
        for(int i = 0; i < EDGE_AMOUNT; i ++){
            st = new StringTokenizer(brRoads.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            int speedLimit = Integer.parseInt(st.nextToken());

            WEdge wEdge = new WEdge(node[to], (WEdge)node[from].edge1, weight, length, speedLimit);
            node[from].edge1 = wEdge;
        }
        System.out.println("Ferdig lest fra fil!");
    }

    private void readCoordinates(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        NODE_AMOUNT = Integer.parseInt(st.nextToken());
        node = new Node[NODE_AMOUNT];

        for(int i = 0; i < NODE_AMOUNT; i ++){
            node[i] = new Node(i);
        }

        for(int i = 0; i < NODE_AMOUNT; i ++){
            st = new StringTokenizer(br.readLine());
            int nodeNr = Integer.parseInt(st.nextToken());
            double latitude = Double.parseDouble(st.nextToken()) * (Math.PI/180);
            double longitude = Double.parseDouble(st.nextToken()) * (Math.PI/180);

            node[nodeNr].latitude = latitude;
            node[nodeNr].longitude = longitude;
        }
    }

    private void readPointsOfInterest(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());

        int pointsOfInterest = Integer.parseInt(st.nextToken());

        for(int i = 0; i < pointsOfInterest; i ++){
            st = new StringTokenizer(br.readLine());
            int nodeNr = Integer.parseInt(st.nextToken());
            int code = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            node[nodeNr].code = code;
            node[nodeNr].name = name;
        }
    }


    public void initializePredecessorsDijkstra(Node start){
        for(int i = NODE_AMOUNT; i-- > 0;){
            node[i].d = new Predecessor();
        }
        ((Predecessor)start.d).distance = 0;
    }



    public void abbreviate(Node n, WEdge wEdge, PriorityQueue<Node> priorityQueue){
        Predecessor nd = (Predecessor)n.d;
        Predecessor md = (Predecessor)wEdge.to.d;

        if(md.distance > nd.distance + wEdge.weight){
            md.distance = nd.distance + wEdge.weight;
            md.combinedHeuristicDistance = md.distance + md.heuristicDistance;
            md.predecessor = n;
            priorityQueue.remove(wEdge.to);
            priorityQueue.add(wEdge.to);
        }
    }


    public Node dijkstra(Node start, Node goal){
        nodePolledCounter = 0;
        initializePredecessorsDijkstra(start);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(NODE_AMOUNT, (a, b) -> (((Predecessor)a.d).distance - ((Predecessor)b.d).distance));
        priorityQueue.add(start);

        while(priorityQueue.size() > 0){
            nodePolledCounter++;
            Node n = priorityQueue.poll();
            for (WEdge wEdge = (WEdge) n.edge1; wEdge != null; wEdge = (WEdge) wEdge.next) {
                abbreviate(n, wEdge, priorityQueue);
            }
            if (n == goal) {
                return n;
            }
        }
        System.out.println("End location is not reachable from start location");
        return null;
    }

    public Node[] dijkstraFindClosest(Node start, int code, int amount){
        Node[] closestNodes = new Node[amount];
        int counter = 0;

        initializePredecessorsDijkstra(start);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(NODE_AMOUNT, (a, b) -> (((Predecessor)a.d).distance - ((Predecessor)b.d).distance));
        priorityQueue.add(start);

        while(priorityQueue.size() > 0){
            Node n = priorityQueue.poll();
            for(WEdge wEdge = (WEdge)n.edge1; wEdge != null; wEdge = (WEdge) wEdge.next){
                abbreviate(n, wEdge, priorityQueue);
            }
            if(n.code == code || n.code == 6){
                closestNodes[counter] = n;
                counter++;
            }
            if (counter == amount) {
                return closestNodes;
            }
        }
        System.out.println("No interest points of this type around the start area.");
        return null;
    }

    public Node Astar(Node start, Node goal){
        nodePolledCounter = 0;
        initializePredecessorsAstar(start, goal);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(NODE_AMOUNT, (a, b) -> (((Predecessor)a.d).combinedHeuristicDistance - ((Predecessor)b.d).combinedHeuristicDistance));

        priorityQueue.add(start);

        while(priorityQueue.size() > 0) {
            nodePolledCounter++;
            Node n = priorityQueue.poll();
            for (WEdge wEdge = (WEdge) n.edge1; wEdge != null; wEdge = (WEdge) wEdge.next) {
                abbreviate(n, wEdge, priorityQueue);
            }
            if (n == goal) {
                return n;
            }
        }
        System.out.println("End location is not reachable from start location");
        return null;
    }


    public void initializePredecessorsAstar(Node start, Node goal){
        for(int i = NODE_AMOUNT; i-- > 0;){
            node[i].d = new Predecessor();
            ((Predecessor)node[i].d).heuristicDistance = estimateLengthHaversine(node[i], goal);
        }
        ((Predecessor)start.d).distance = 0;
    }


    int estimateLengthHaversine (Node n1, Node n2) {
        double sin_latitude = Math.sin((n1.latitude-n2.latitude)/2.0);
        double sin_longitude = Math.sin((n1.longitude-n2.longitude)/2.0);
        return (int) (35285538.46153846153846153846*Math.asin(Math.sqrt((sin_latitude*sin_latitude)+(Math.cos(n1.latitude)*Math.cos(n2.latitude)*sin_longitude*sin_longitude))));
    }


    public void printRoad(Node n){
        for(Node node = n; node != null; node = ((Predecessor)node.d).predecessor){
            node.printCoordinates();
        }
    }

    public void printRoadInfo(Node n){
        double time = 0;
        int length = 0;

        for(Node node = n; node != null && ((Predecessor)node.d).predecessor != null; node = ((Predecessor)node.d).predecessor){
            for(WEdge wEdge = (WEdge)((Predecessor)node.d).predecessor.edge1; wEdge != null; wEdge = (WEdge) wEdge.next){
                if(wEdge.to == node){
                    time += ((double)(wEdge.lengthInM) / (double)(wEdge.speedLimit/3.6));
                    length += wEdge.lengthInM;
                    break;
                }
            }
        }
        double hours = time/3600;
        double minutes = (time - (int)hours * 3600)/60;
        double seconds = time - ((int)hours * 3600) - ((int)minutes * 60);
        System.out.println("Expected time: " + (int)hours + " hours " + (int)minutes + " minutes " + (int)seconds + " seconds\nLenght: " + length/1000 + " km.");
    };


    public Node[] getNode() {
        return node;
    }

    public int getNodePolledCounter() {
        return nodePolledCounter;
    }
}
