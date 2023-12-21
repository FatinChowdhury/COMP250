import java.io.Serializable;
import java.util.ArrayList;
import java.text.*;
import java.lang.Math;

public class DecisionTree implements Serializable {

	DTNode rootDTNode;
	int minSizeDatalist; //minimum number of data points that should be present in the dataset to initiate a split

	// Mention the serialVersionUID explicitly in order to avoid getting errors while deserializing.
	public static final long serialVersionUID = 343L;

	public DecisionTree(ArrayList<Datum> datalist , int min) {
		minSizeDatalist = min; // this is K from the algorithm
		rootDTNode = (new DTNode()).fillDTNode(datalist);
	}


	class DTNode implements Serializable{
		//Mention the serialVersionUID explicitly in order to avoid getting errors while deserializing.
		public static final long serialVersionUID = 438L;
		boolean leaf;
		int label = -1;      // only defined if node is a leaf, for node
		int attribute; // only defined if node is not a leaf, this is the index that tells us which of the attribute we're looking at for a datum
		double threshold;  // only defined if node is not a leaf, we will compare this with int attribute for the data points

		// for example: we are comparing X values in coordinate against a threshold, if the X value < threshold, we go left, otherwise we go right

		DTNode left, right; //the left and right child of a particular node. (null if leaf)

		DTNode() {
			leaf = true;
			threshold = Double.MAX_VALUE;
		}


		// this method takes in a datalist (ArrayList of type datum). It returns the calling DTNode object
		// as the root of a decision tree trained using the data points present in the datalist variable and minSizeDatalist.
		// Also, KEEP IN MIND that the left and right child of the node correspond to "less than" and "greater than or equal to" threshold


		// if your data set has less than minSizeDatalist, make the boolean leaf = true and then return a leaf node with the majority label.
		// if you have more than K, then you have enough to split the data in two, but do not split if they all have the same label
		DTNode fillDTNode(ArrayList<Datum> datalist) {
			// if the labelled data set has at least K data items (K represents minSizeDatalist)
			if (datalist.size() >= minSizeDatalist){ // outer if statement
				boolean allSame = true;
				Datum firstValue = null;
				for (Datum d: datalist) {
					if (firstValue == null) {
						firstValue = d;
					} else if (d.y != firstValue.y) {
						allSame = false;
						break;
					}
				}
				if (allSame == true){
					DTNode newLeafNode = new DTNode();
					newLeafNode.leaf = true;
					newLeafNode.label = datalist.get(0).y;
					return newLeafNode;
				}
				else{
					// creating "best" attribute test question, where we create an internal node, in which we need an attribute cutoff to split dataset
					ArrayList<Object> bestSplit = findBestSplit(datalist);
					int bestAttribute = (int) bestSplit.get(0);
					double bestThreshold = (double) bestSplit.get(1);

					ArrayList<Datum> leftNode = new ArrayList<>();
					ArrayList<Datum> rightNode = new ArrayList<>();
					for (Datum d: datalist){
						if (d.x[bestAttribute] < bestThreshold){
							leftNode.add(d);
						} else {
							rightNode.add(d);
						}
					}
					DTNode newInternalNode = new DTNode();
					newInternalNode.leaf = false;
					newInternalNode.attribute = bestAttribute;
					newInternalNode.threshold = bestThreshold;

					newInternalNode.left = fillDTNode(leftNode);
					newInternalNode.right = fillDTNode(rightNode);



					return newInternalNode;
				}
			}

			// outer else
			DTNode newLeafNode = new DTNode();
			newLeafNode.leaf = true;
			newLeafNode.label = findMajority(datalist);
			return newLeafNode;
		}



		// This is a helper method. Given a datalist, this method returns the label that has the most
		// occurrences. In case of a tie it returns the label with the smallest value (numerically) involved in the tie.
		int findMajority(ArrayList<Datum> datalist) {

			int [] votes = new int[2];

			//loop through the data and count the occurrences of data points of each label
			for (Datum data : datalist)
			{
				votes[data.y]+=1;
			}

			if (votes[0] >= votes[1])
				return 0;
			else
				return 1;
		}




		// This method takes in a datapoint (excluding the label) in the form of an array of type double (Datum.x) and
		// returns its corresponding label, as determined by the decision tree
		int classifyAtNode(double[] xQuery) {

			if (this.leaf){
				return this.label;
			}

			if (xQuery[this.attribute] < this.threshold){
				return this.left.classifyAtNode(xQuery);
			}else{
				return this.right.classifyAtNode(xQuery);
			}
			//ADD CODE HERE

			// return -1; //dummy code.  Update while completing the assignment.
		}


		//given another DTNode object, this method checks if the tree rooted at the calling DTNode is equal to the tree rooted
		public boolean equals(Object dt2)
		{
			if (!(dt2 instanceof DTNode)){return false;}

			DTNode dtNode2 = (DTNode) dt2;

			if (this.leaf && dtNode2.leaf){
				return this.label == dtNode2.label;
			}

			if (this.attribute == dtNode2.attribute && this.threshold == dtNode2.threshold){
				return (this.left.equals(dtNode2.left) && this.right.equals(dtNode2.right));
			}
			//ADD CODE HERE

			return false; //dummy code.  Update while completing the assignment.
		}

	}



	//Given a dataset, this returns the entropy of the dataset
	double calcEntropy(ArrayList<Datum> datalist) {
		double entropy = 0;
		double px = 0;
		float [] counter= new float[2];
		if (datalist.size()==0)
			return 0;
		double num0 = 0.00000001,num1 = 0.000000001;

		//calculates the number of points belonging to each of the labels
		for (Datum d : datalist)
		{
			counter[d.y]+=1;
		}
		//calculates the entropy using the formula specified in the document
		for (int i = 0 ; i< counter.length ; i++)
		{
			if (counter[i]>0)
			{
				px = counter[i]/datalist.size();
				entropy -= (px*Math.log(px)/Math.log(2));
			}
		}

		return entropy;
	}


	// given a datapoint (without the label) calls the DTNode.classifyAtNode() on the root node of the calling DecisionTree object
	int classify(double[] xQuery ) {
		return this.rootDTNode.classifyAtNode( xQuery );
	}

	// Checks the performance of a DecisionTree on a dataset
	// This method is provided in case you would like to compare your
	// results with the reference values provided in the PDF in the Data
	// section of the PDF
	String checkPerformance( ArrayList<Datum> datalist) {
		DecimalFormat df = new DecimalFormat("0.000");
		float total = datalist.size();
		float count = 0;

		for (int s = 0 ; s < datalist.size() ; s++) {
			double[] x = datalist.get(s).x;
			int result = datalist.get(s).y;
			if (classify(x) != result) {
				count = count + 1;
			}
		}

		return df.format((count/total));
	}


	//Given two DecisionTree objects, this method checks if both the trees are equal by
	//calling onto the DTNode.equals() method
	public static boolean equals(DecisionTree dt1,  DecisionTree dt2)
	{
		boolean flag = true;
		flag = dt1.rootDTNode.equals(dt2.rootDTNode);
		return flag;
	}
	private ArrayList<Object> findBestSplit(ArrayList<Datum> datalist){
		// create int best_avg_entropy := infinity
		double best_avg_entropy = Double.MAX_VALUE;
		int best_attribute = -1;
		double best_threshold = -1;
		// for each attribute in x

		for (int i = 0; i < datalist.get(0).x.length; i++){

			for (Datum d: datalist){ // for each data point in list
				//compute split and current_avg_entropy based on that split (we always want split to give us the lowest average entropy)
				ArrayList<Datum> leftSplit = new ArrayList<>();
				ArrayList<Datum> rightSplit = new ArrayList<>();
				for (Datum splitDatum: datalist) {
					if (splitDatum.x[i] < d.x[i]) {
						leftSplit.add(splitDatum);
					} else {
						rightSplit.add(splitDatum);
					}
				}
				double leftEntropy = calcEntropy(leftSplit);
				double rightEntropy = calcEntropy(rightSplit);
				double avgEntropy = (leftEntropy * leftSplit.size() + rightEntropy * rightSplit.size()) / datalist.size();

				// if statements
				if (best_avg_entropy > avgEntropy){
					best_avg_entropy = avgEntropy;
					best_attribute = i; // best_attr := attribute;
					best_threshold = d.x[i];	// best_threshold = value;
				}
			}


		}
		ArrayList<Object> result = new ArrayList<>();
		result.add(best_attribute);
		result.add(best_threshold);
		return result;
	}


	private static void printNStr(int n, String str) {
		for ( int i = 0 ; i< n ; i++ ) {
			System.out.printf("%s",str);
		}
	}

	private void logNode( DTNode currentNode, int depth, String leftOrRight) {
        /*
        boolean leaf;
        int label = -1; // only defined if node is a leaf. red or green.
        int attribute; // only defined if node is not a leaf, index i : x_i.
        double threshold; // only defined if node is not a leaf : x_i < threshold. (Left) , x_i > threshold, right.
        DTNode left, right; // the left and right child of a particular node. (null if leaf)
        */

		if ( currentNode.leaf ) {
			printNStr( depth, " ");
			System.out.printf("%s", "{ ");
			System.out.printf("%s", "depth: " + depth + ",   "  );
			System.out.printf("%s", "leaf: " + currentNode.leaf + ",   "   );
			System.out.printf("%s", "label: " + currentNode.label + ",   "   );
			System.out.printf("%s",leftOrRight + " },\n");
			return;
		}

		printNStr( depth, " ");
		System.out.printf("%s", "{ ");
		System.out.printf("%s", "depth: " + depth + ",   "  );
		System.out.printf("%s", "attribute: " + currentNode.attribute + ",   "  );
		System.out.printf("%s", "threshold: " + currentNode.threshold + ",   "  );
		System.out.printf("%s", "leaf: " + currentNode.leaf + ",   "   );
		System.out.printf("%s",leftOrRight + " },\n");

		logNode(currentNode.left, depth+1, "left");
		logNode(currentNode.right, depth+1, "right");
	}
	void logTree() {
		logNode(rootDTNode, 0, "root");
	}

}
