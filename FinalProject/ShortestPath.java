package finalproject;


import java.util.ArrayList;

import finalproject.system.Tile;

public class ShortestPath extends PathFindingService {
    //TODO level 4: find distance prioritized path
    public ShortestPath(Tile start) {
        super(start);
        generateGraph();
    }

    @Override
    public void generateGraph() {

        // Task 1: Step 1: In ShortestPath, implement generateGraph(). The method creates a weighted
        // graph using the distance cost as weight. This graph should be then stored in the appropriate field.
        // To make sure that the graph is generated each time a ShortestPath object is created, you should
        // add a call to this method inside the constructor.


        // 1. create a Graph with all the Graph's methods you have already implemented before

        g = new Graph(new ArrayList<>());

        // 2. this new Graph should contain all reachable Tiles from the source Tile (the source Tile is the parameter start in the constructor).
        // Extra note for step 2: you can find all reachable Tiles using BFS or DFS, which you have previously implemented.
        // another extra note for step 2: You can import ArrayList into this class (ArrayList is an exception to the import rule of the instructions).

        ArrayList<Tile> reachableTiles = GraphTraversal.DFS(source);

        for (Tile tile : reachableTiles) {
            g.tiles.add(tile);
        }

        // 3. There is an edge between two Tiles of this new Graph if the Tiles are adjacent and both Tiles are walkable.
        // extra note for step 3: You can use the neighbors field of the Tile class to get the adjacent Tiles. You can use isWalkable to check if a Tile is walkable.

        for (Tile tile : reachableTiles) {
            for (Tile neighbor : tile.neighbors) {
                if (neighbor.isWalkable()) {
                    double weight = g.calculateEdgeWeightDistanceCost(tile, neighbor);
                    g.addEdge(tile, neighbor, weight);
                }
            }
        }


        // 4. You should store this new Graph in the field g in PathFindingService, the superclass of ShortestPath, done.
    }

}
