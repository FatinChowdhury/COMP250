package finalproject;

import finalproject.system.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class PathFindingService {
    Tile source;
    Graph g;

    public PathFindingService(Tile start) {
        this.source = start;
    }

    public abstract void generateGraph();

    private void initSingleSource(Tile startNode) {
        for (Tile tile : g.tiles) {
            tile.costEstimate = Integer.MAX_VALUE;
            tile.predecessor = null;
        }
        startNode.costEstimate = 0;
    }
    private void relax(Tile u, Tile v) {
        double weight = g.getWeight(u, v);
        if (v.costEstimate > u.costEstimate + weight) {
            v.costEstimate = u.costEstimate + weight;
            v.predecessor = u;
        }
    }

    //TODO level 4: Implement basic dijkstra's algorithm to find a path to the final unknown destination
    public ArrayList<Tile> findPath(Tile startNode) {


        for (Tile tile : g.tiles) {
            tile.costEstimate = Double.MAX_VALUE;
            tile.predecessor = null;
        }
        // Set the cost estimate of the start node to 0
        startNode.costEstimate = 0;

        // Create a priority queue and add the start node
        TilePriorityQ queue = new TilePriorityQ(g.tiles);

        while (!queue.isEmpty()) {
            // Remove the tile with the smallest cost estimate
            Tile u = queue.removeMin();

            // For each neighbor v of u
            for (Tile v : g.getNeighbors(u)) {
                // Calculate the tentative cost estimate of v
                double alt = u.costEstimate + g.getWeight(u, v);

                // If the tentative cost estimate is smaller than the current cost estimate of v
                if (alt < v.costEstimate) {
                    // Update the cost estimate and predecessor of v
                    v.costEstimate = alt;
                    v.predecessor = u;

                    // Update the cost estimate of v in the queue
                    queue.updateKeys(v, u, v.costEstimate);
                }
            }
        }

        // Construct the shortest path
        ArrayList<Tile> path = new ArrayList<>();
        Tile destination = null;
        for (Tile tile : g.tiles) {
            if (tile.isDestination) {
                destination = tile;
                break;
            }
        }

        if (destination != null) {
            Tile tile = destination;
            while (tile != null) {
                path.add(0, tile);
                tile = tile.predecessor;
            }
        }

        return path;
//        initSingleSource(startNode);
//
//
//        // creating TilePriorityQ with all vertices
//        TilePriorityQ queue = new TilePriorityQ(g.tiles);
//
//        while (!queue.isEmpty()) {
//            // Remove tile with minimum cost estimate
//
//            Tile tile = queue.removeMin();
//
//            // relax the edges of the removed tile
//            for (Tile neighbor : g.getNeighbors(tile)) {
//                relax(tile, neighbor);
//                queue.updateKeys(neighbor, tile, neighbor.costEstimate);
//            }
//        }
//
//        ArrayList<Tile> path = new ArrayList<>();
//        Tile destination = null;
//        for (Tile tile : g.tiles) {
//            if (tile.isDestination) {
//                destination = tile;
//                break;
//            }
//        }
//
//        if (destination != null) {
//            Tile tile = destination;
//            while (tile != null) {
//                path.add(0, tile);
//                tile = tile.predecessor;
//            }
//        }
//        return path;
    }



    //TODO level 5: Implement basic dijkstra's algorithm to path find to a known destination
    public ArrayList<Tile> findPath(Tile start, Tile end) {
        // Initialize all tiles
        for (Tile tile : g.tiles) {
            tile.costEstimate = Double.MAX_VALUE;
            tile.predecessor = null;
        }
        // Set the cost estimate of the start node to 0
        start.costEstimate = 0;

        // Create a priority queue and add the start node
        TilePriorityQ queue = new TilePriorityQ(g.tiles);

        while (!queue.isEmpty()) {
            // Remove the tile with the smallest cost estimate
            Tile u = queue.removeMin();

            // If the removed tile is the destination, we're done
            if (u.equals(end)) {
                break;
            }

            // For each neighbor v of u
            for (Tile v : g.getNeighbors(u)) {
                // Calculate the tentative cost estimate of v
                double alt = u.costEstimate + g.getWeight(u, v);

                // If the tentative cost estimate is smaller than the current cost estimate of v
                if (alt < v.costEstimate) {
                    // Update the cost estimate and predecessor of v
                    v.costEstimate = alt;
                    v.predecessor = u;

                    // Update the cost estimate of v in the queue
                    queue.updateKeys(v, u, v.costEstimate);
                }
            }
        }

        // Construct the shortest path
        ArrayList<Tile> path = new ArrayList<>();
        Tile tile = end;
        while (tile != null) {
            path.add(0, tile);
            tile = tile.predecessor;
        }

        return path;
    }

    //TODO level 5: Implement basic dijkstra's algorithm to path find to the final destination passing through given waypoints
    public ArrayList<Tile> findPath(Tile start, LinkedList<Tile> waypoints){

        ArrayList<Tile> path = new ArrayList<>();
        Tile currentStart = start;

        // Iterate over each waypoint
        for (Tile waypoint : waypoints) {
            // Find the shortest path to the current waypoint
            ArrayList<Tile> subPath = findPath(currentStart, waypoint);
            // Add the subpath to the overall path
            // We remove the last element to avoid adding the waypoint twice
            if (!subPath.isEmpty()) {
                subPath.remove(subPath.size() - 1);
            }
            path.addAll(subPath);
            // Update the current start to be the waypoint
            currentStart = waypoint;
        }

        // Find the destination tile
        Tile destination = null;
        for (Tile tile : g.tiles) {
            if (tile.isDestination) {
                destination = tile;
                break;
            }
        }

        // If a destination was found, find the shortest path to it
        if (destination != null) {
            ArrayList<Tile> subPath = findPath(currentStart, destination);
            path.addAll(subPath);
        }

        return path;
    }

}

