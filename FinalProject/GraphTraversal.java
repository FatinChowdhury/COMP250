package finalproject;

import finalproject.system.Tile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class GraphTraversal
{


	//TODO level 1: implement BFS traversal starting from s
	public static ArrayList<Tile> BFS(Tile s){

		ArrayList<Tile> resultBFS = new ArrayList<>();
		LinkedList<Tile> queue = new LinkedList<>();
		HashSet<Tile> visited = new HashSet<>();

		queue.add(s);
		visited.add(s);

		while (!queue.isEmpty()) {
			Tile currentTile = queue.poll();
			resultBFS.add(currentTile);

			for (Tile neighbor : currentTile.neighbors) {
				if (!visited.contains(neighbor) && neighbor.isWalkable()){
					queue.add(neighbor);
					visited.add(neighbor);
				}
			}
		}

		return resultBFS;
	}


	//TODO level 1: implement DFS traversal starting from s
	public static ArrayList<Tile> DFS(Tile s) {

		ArrayList<Tile> resultDFS = new ArrayList<>();
		LinkedList<Tile> stack = new LinkedList<>();
		HashSet<Tile> visited = new HashSet<>();

		stack.push(s);
		visited.add(s);

		while(!stack.isEmpty()){
			Tile currentTile = stack.pop();
			resultDFS.add(currentTile);

			for (Tile neighbor : currentTile.neighbors){
				if(!visited.contains(neighbor) && neighbor.isWalkable()){
					stack.push(neighbor);
					visited.add(neighbor);
				}
			}
		}
		return resultDFS;
	}

}
