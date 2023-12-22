package finalproject;

import java.util.ArrayList;
import java.util.HashSet;

import finalproject.system.Tile;
import finalproject.system.TileType;
import finalproject.tiles.*;

public class Graph {
    ArrayList<Tile> tiles;
    ArrayList<Edge> allEdges;

    // weight(Edge(t1,t2)) = cost(t2)
    // weight(Edge(t2,t1)) = cost(t1)

    // TODO level 2: Add fields that can help you implement this data type

    // TODO level 2: initialize and assign all variables inside the constructor
    public Graph(ArrayList<Tile> vertices) {
        this.tiles = vertices;
        this.allEdges = new ArrayList<>();
        // we expect this constructor to create a graph with the given set of nodes (provided as input) and no edges.

    }

    // TODO level 2: add an edge to the graph
    public void addEdge(Tile origin, Tile destination, double weight){
        // we expect this method to add an edge between the given nodes (provided as input) with the given weight.
        Edge newEdge = new Edge(origin, destination, weight);
        allEdges.add(newEdge);

    }

    // TODO level 2: return a list of all edges in the graph
    public ArrayList<Edge> getAllEdges() {
        // ArrayList<Edge> allEdges = new ArrayList<>();
        // for (Tile tile : tiles) {
        //     for (Tile neighbor : tile.neighbors) {
        //         allEdges.add(new Edge(tile, neighbor, neighbor.distanceCost));
        //     }
        // }
        return allEdges;
    }

    // TODO level 2: return list of tiles adjacent to t
    public ArrayList<Tile> getNeighbors(Tile t) {

        ArrayList<Tile> tilesNeighbors = new ArrayList<>();

        for (Edge edge : allEdges) {
            if (edge.getStart().equals(t)) {
                tilesNeighbors.add(edge.getEnd());
            }
        }
        return tilesNeighbors;
    }


    // computePathCost method (before) summed up the costEstimate of each Tile in the path
    // but if the costEstimate of each Tile does not represent the cost of the edge leaeding to that Tile, the method will not work properly
    private Edge getEdge(Tile origin, Tile destination) {
        for (Edge edge : allEdges) {
            if (edge.getStart().equals(origin) && edge.getEnd().equals(destination)) {
                return edge;
            }
        }
        return null;  // Return null if no edge exists between the given tiles
    }

    public double computePathCost(ArrayList<Tile> path) {
        double totalCost = 0.0;
        for (int i = 0; i < path.size() - 1; i++) {
            Edge edge = getEdge(path.get(i), path.get(i + 1));
            if (edge != null) {
                totalCost += edge.weight;
            }
        }
        return totalCost;
    }


    public double getWeight(Tile u, Tile v) {
        for (Edge edge : allEdges) {
            if (edge.getStart().equals(u) && edge.getEnd().equals(v)) {
                return edge.weight;
            }
        }
        return Double.MAX_VALUE;  // Return a large value if no edge exists between the given tiles
    }

    public double calculateEdgeWeightTimeCost(Tile start, Tile end) {
        if (start instanceof MetroTile && end instanceof MetroTile) {
            MetroTile metroStart = (MetroTile) start;
            metroStart.fixMetro(end);
            return metroStart.metroTimeCost;
        } else {
            return end.timeCost;
        }
    }

    public double calculateEdgeWeightDistanceCost(Tile start, Tile end) {
        if (start instanceof MetroTile && end instanceof MetroTile) {
            MetroTile metroStart = (MetroTile) start;
            metroStart.fixMetro(end);
            return metroStart.metroDistanceCost;
        } else {
            return end.distanceCost;
        }
    }


    public static class Edge{
        Tile origin;
        Tile destination;
        double weight;

        // TODO level 2: initialize appropriate fields
        public Edge(Tile s, Tile d, double cost){
            this.origin = s;
            this.destination = d;
            this.weight = cost;
        }

        // TODO level 2: getter function 1
        public Tile getStart(){
            return this.origin;
        }


        // TODO level 2: getter function 2
        public Tile getEnd() {
            return this.destination;
        }

    }

}
