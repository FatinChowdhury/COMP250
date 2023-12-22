package finalproject;

import java.util.ArrayList;
import java.util.Arrays;


import finalproject.system.Tile;

public class TilePriorityQ {
	private ArrayList<Tile> heap;
	// TODO level 3: Add fields that can help you implement this data type

	// TODO level 3: implement the constructor for the priority queue
	public TilePriorityQ (ArrayList<Tile> vertices) {
		this.heap = new ArrayList<>(vertices);
		buildMinHeap();
	}

	private void buildMinHeap() {
		for (int i = heap.size() / 2; i >= 0; i--) {
			minHeapify(i);
		}
	}

	private void minHeapify(int i) {
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		int smallest = i;

		if (left < heap.size() && heap.get(left).costEstimate < heap.get(smallest).costEstimate) {
			smallest = left;
		}

		if (right < heap.size() && heap.get(right).costEstimate < heap.get(smallest).costEstimate) {
			smallest = right;
		}

		if (smallest != i) {
			Tile temp = heap.get(i);
			heap.set(i, heap.get(smallest));
			heap.set(smallest, temp);

			minHeapify(smallest);
		}
	}

	// TODO level 3: implement remove min as seen in class
	public Tile removeMin() {
		if (heap.size() == 0) {
			return null;
		}
		Tile min = heap.get(0);
		heap.set(0, heap.get(heap.size() - 1));
		heap.remove(heap.size() - 1);
		minHeapify(0);

		return min;
	}

	// TODO level 3: implement updateKeys as described in the pdf
	public void updateKeys(Tile t, Tile newPred, double newEstimate) {
		int index = heap.indexOf(t);
		if (index != -1) {
			t.predecessor = newPred;
			t.costEstimate = newEstimate;

			while (index > 0 && heap.get((index - 1) / 2).costEstimate > heap.get(index).costEstimate) {
				Tile temp = heap.get(index);
				heap.set(index, heap.get((index - 1) / 2));
				heap.set((index - 1) / 2, temp);

				index = (index - 1) / 2;
			}
		}
	}

	public boolean isEmpty() {
		return heap.isEmpty();
	}
}
