package finalproject;


import java.util.ArrayList;
import java.util.LinkedList;

import finalproject.system.Tile;

public class SafestShortestPath extends ShortestPath {
	public int health;
	public Graph costGraph;
	public Graph damageGraph;
	public Graph aggregatedGraph;

	//TODO level 8: finish class for finding the safest shortest path with given health constraint
	public SafestShortestPath(Tile start, int health) {
		super(start);
		this.health = health;
		generateGraph();
	}


	public void generateGraph() {
		// Initialize costGraph with distance cost
		costGraph = new Graph(new ArrayList<>());
		ArrayList<Tile> reachableTiles = GraphTraversal.DFS(source);
		for (Tile tile : reachableTiles) {
			costGraph.tiles.add(tile);
			for (Tile neighbor : tile.neighbors) {
				if (neighbor.isWalkable()) {
					double weight = costGraph.calculateEdgeWeightDistanceCost(tile, neighbor);
					costGraph.addEdge(tile, neighbor, weight);
				}
			}
		}

		// Initialize damageGraph with damage cost
		damageGraph = new Graph(new ArrayList<>());
		for (Tile tile : reachableTiles) {
			damageGraph.tiles.add(tile);
			for (Tile neighbor : tile.neighbors) {
				if (neighbor.isWalkable()) {
					double weight = neighbor.damageCost;  // Use damage cost for weight
					damageGraph.addEdge(tile, neighbor, weight);
				}
			}
		}

		// Initialize aggregatedGraph with damage cost
		aggregatedGraph = new Graph(new ArrayList<>());
		for (Tile tile : reachableTiles) {
			aggregatedGraph.tiles.add(tile);
			for (Tile neighbor : tile.neighbors) {
				if (neighbor.isWalkable()) {
					double weight = neighbor.damageCost;  // Use damage cost for weight
					aggregatedGraph.addEdge(tile, neighbor, weight);
				}
			}
		}
	}

	public ArrayList<Tile> findPath(Tile start, LinkedList<Tile> waypoints) {
		// Step a)
		g = costGraph;
		ArrayList<Tile> pc = super.findPath(start, waypoints);
		double pcCost = g.computePathCost(pc);
		double pcDamage = damageGraph.computePathCost(pc);
		if (pcDamage <= health) {
			return pc;
		}

		// Step b)
		g = damageGraph;
		ArrayList<Tile> pd = super.findPath(start, waypoints);
		double pdDamage = g.computePathCost(pd);
		if (pdDamage > health) {
			return null;
		}

		// Call the recursive method
		return findPathRecursively(start, waypoints, pc, pd, pcCost, pcDamage, pdDamage);
	}

	private ArrayList<Tile> findPathRecursively(Tile start, LinkedList<Tile> waypoints, ArrayList<Tile> pc, ArrayList<Tile> pd, double pcCost, double pcDamage, double pdDamage) {
		// Step c)
		double lambda = (pcCost - pdDamage) / (pdDamage - pcDamage);
		for (Graph.Edge edge : aggregatedGraph.allEdges) {
			double c = costGraph.getWeight(edge.getStart(), edge.getEnd());
			double d = damageGraph.getWeight(edge.getStart(), edge.getEnd());
			edge.weight = c + lambda * d;
		}

		// Step d)
		g = aggregatedGraph;
		ArrayList<Tile> pr = super.findPath(start, waypoints);
		double prAggregate = g.computePathCost(pr);
		double prDamage = damageGraph.computePathCost(pr);
		if (prAggregate == pcCost) {
			return pd;
		} else if (prDamage <= health) {
			return findPathRecursively(start, waypoints, pr, pd, prAggregate, prDamage, pdDamage);
		} else {
			return findPathRecursively(start, waypoints, pr, pc, prAggregate, prDamage, pcDamage);
		}
	}
}
